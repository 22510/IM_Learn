package com.qehing.client;

import com.qehing.client.handlers.*;
import com.qehing.console.ConsoleCommandManager;
import com.qehing.console.LoginConsoleCommand;
import com.qehing.protocols.Judge;
import com.qehing.protocols.PacketDecoder;
import com.qehing.protocols.PacketEncoder;
import com.qehing.server.handlers.IMIdleStateHandler;
import com.qehing.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyRemotingClient {
    static final String SERVER_HOST = "127.0.0.1";
    //    static final int CLIENT_PORT;
    static final int SERVER_PORT = 8686;
    static final int SIZE = 256;
    static final int MAX_RETRY_TIMES = 10;

//    static {
//        try {
//            CLIENT_PORT = new ServerSocket(0).getLocalPort();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void start() {
//        System.out.println(CLIENT_PORT);
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new NettyRemotingClientHandler(bootstrap));
//                            pipeline.addLast(new NettyRemotingClientHandler(bootstrap));
//                            pipeline.addLast(new Judge());
//                            pipeline.addLast(new PacketDecoder());
//                            pipeline.addLast(new LoginResponseHandler());
//                            pipeline.addLast(new CreateGroupResponseHandler());
//                            pipeline.addLast(new JoinGroupResponseHandler());
//                            pipeline.addLast(new MessageResponseHandler());
//                            pipeline.addLast(new QuitGroupResponseHandler());
//                            pipeline.addLast(new PacketEncoder());
                            // 空闲检测
                            ch.pipeline().addLast(new IMIdleStateHandler());

                            ch.pipeline().addLast(new Judge());
                            ch.pipeline().addLast(new PacketDecoder());
                            // 登录响应处理器
                            ch.pipeline().addLast(new LoginResponseHandler());
                            // 收消息处理器
                            ch.pipeline().addLast(new MessageResponseHandler());
                            // 创建群响应处理器
                            ch.pipeline().addLast(new CreateGroupResponseHandler());
                            // 加群响应处理器
                            ch.pipeline().addLast(new JoinGroupResponseHandler());
                            // 退群响应处理器
                            ch.pipeline().addLast(new QuitGroupResponseHandler());
                            // 获取群成员响应处理器
                            ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                            // 群消息响应
                            ch.pipeline().addLast(new GroupMessageResponseHandler());
                            // 登出响应处理器
                            ch.pipeline().addLast(new LogoutResponseHandler());
                            ch.pipeline().addLast(new PacketEncoder());

                            // 心跳定时器
                            ch.pipeline().addLast(new HeartBeatTimerHandler());
                        }
                    });

            connect(bootstrap, MAX_RETRY_TIMES);

//            ChannelFuture future = bootstrap.connect(SERVER_HOST, SERVER_PORT).sync();
//            System.out.println(future.isSuccess());
//            connect(bootstrap, SERVER_HOST, SERVER_PORT, MAX_RETRY_TIMES);
//            bootstrap.connect(SERVER_HOST, PORT).sync();
//            String cli_msg = "Hello World!";
//            future.channel().writeAndFlush(cli_msg);
//            future.channel().closeFuture().sync();

//            ChannelFuture future = bootstrap.connect(SERVER_HOST, SERVER_PORT);
//            future.addListener((ChannelFutureListener) channelFuture -> {
//                if (!channelFuture.isSuccess()) {
//                    final EventLoop loop = channelFuture.channel().eventLoop();
//                    loop.schedule(() -> {
//                        System.err.println("服务端链接不上，开始重连操作...");
//                        try {
//                            bootstrap.connect(SERVER_HOST, SERVER_PORT);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }, 3, TimeUnit.SECONDS);
//                } else {
//                    System.err.println("服务端链接成功...");
//                }
//            });
//            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            Thread.sleep(3000);
//            group.shutdownGracefully();
            Runtime.getRuntime().addShutdownHook(new Thread(group::shutdownGracefully));
        }
    }

    /**
     * 连接服务端：设置重连机制
     *
     * @param bootstrap
     * @param retry
     */
    static void connect(Bootstrap bootstrap, int retry) throws InterruptedException {
//        ChannelFuture future = bootstrap.connect(NettyRemotingClient.SERVER_HOST, NettyRemotingClient.SERVER_PORT);
        bootstrap.connect(NettyRemotingClient.SERVER_HOST, NettyRemotingClient.SERVER_PORT).addListener((ChannelFutureListener) connect_future -> {
//            System.out.println(connect_future.isSuccess());
            if (connect_future.isSuccess()) {
                System.out.println("连接服务器{" + NettyRemotingClient.SERVER_HOST + ":" + NettyRemotingClient.SERVER_PORT + "}成功！");
                startConsoleThread(connect_future.channel());
            } else if (retry == 0) {
                System.out.println("重连次数耗尽，放弃重连！");
            } else {
                int order = (MAX_RETRY_TIMES - retry) + 1;
                int delay = 1 << order;
                // 重连时间间隔
                System.err.println(new Date() + ":第" + order + "次连接服务器{" + NettyRemotingClient.SERVER_HOST + ":" + NettyRemotingClient.SERVER_PORT + "}失败！");
                System.err.println("正在进行重连。。。");
                connect_future.channel().eventLoop().schedule(
                        () -> {
                            try {
                                connect(bootstrap, retry - 1);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        , delay, TimeUnit.SECONDS);
            }
        });
//        future.channel().closeFuture().sync();
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner sc = new Scanner(System.in);

        new Thread(() -> {
//            while (!Thread.interrupted()) {
//                if (LoginUtil.isLogin(channel)) {
//                    System.out.println("输出信息到服务端：");
//                    Scanner sc = new Scanner(System.in);
//                    String toUser = sc.nextLine();
//                    MessageRequestPacket packet = new MessageRequestPacket();
//                    packet.setMessage(sc.nextLine());
//                    packet.setToUserId(toUser);
////                    ByteBuf byteBuf = PacketCodeC.encode(channel.alloc().ioBuffer(), packet);
////                    channel.writeAndFlush(byteBuf);
//                    channel.writeAndFlush(packet);
//                }
//            }
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    System.out.println("进行登录：");
                    loginConsoleCommand.exec(sc,channel);
//                    System.out.print("输入用户名登录: ");
//                    String username = sc.nextLine();
//                    loginRequestPacket.setUsername(username);
//
//                    // 密码使用默认的
//                    loginRequestPacket.setPassword("pwd");
//
//                    // 发送登录数据包
//                    channel.writeAndFlush(loginRequestPacket);
//                    waitForLoginResponse();
                } else {
//                    System.out.println("输入对方id");
//                    String toUserId = sc.next();
//                    System.out.println("输入信息");
//                    String message = sc.next();
//                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
//                    System.out.println("消息发送成功");
                    consoleCommandManager.exec(sc, channel);
                }
            }
        }).start();
    }

    private static void waitForLoginResponse(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
