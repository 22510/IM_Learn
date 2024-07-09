package com.qehing.server;

import com.qehing.server.handlerService.ThreadPoolManager;
import com.qehing.server.handlers.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import protocols.Judge;
import protocols.PacketCodecHandler;

public class NettyRemotingServer {
    static final int PORT = 8686;

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                                      @Override
                                      protected void initChannel(SocketChannel ch) {
                                          ChannelPipeline pipeline = ch.pipeline();
//                              pipeline.addLast(new StringDecoder());
//                              pipeline.addLast(new StringEncoder());
//                              pipeline.addLast(new NettyRemotingServerHandler());
                                          pipeline.addLast(new IMIdleStateHandler());
                                          pipeline.addLast(new Judge());
//                                          pipeline.addLast(new PacketDecoder());
                                          pipeline.addLast(PacketCodecHandler.INSTANCE);
                                          pipeline.addLast(LoginRequestHandler.INSTANCE);
                                          pipeline.addLast(RegisterRequestHandler.INSTANCE);
                                          pipeline.addLast(HeartBeatRequestHandler.INSTANCE);
                                          pipeline.addLast(AuthHandler.INSTANCE);
                                          pipeline.addLast(IMHandler.INSTANCE);
//                                          pipeline.addLast(new MessageRequestHandler());
//                                          pipeline.addLast(new CreateGroupRequestHandler());
//                                          pipeline.addLast(new QuitGroupRequestHandler());
//                                          pipeline.addLast(new JoinGroupRequestHandler());
//                                          pipeline.addLast(new PacketEncoder());
                                      }
                                  }
                    );
            ChannelFuture future = bootstrap.bind(PORT).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            ThreadPoolManager.shutdownAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyRemotingServer().start();
    }
}
