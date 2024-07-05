package com.qehing.client;//package com.qehing.client;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
//import java.util.concurrent.TimeUnit;
//
//public class NettyRemotingClientHandler extends ChannelInboundHandlerAdapter {
//
//    private final Bootstrap bootstrap;
//
//    public NettyRemotingClientHandler(Bootstrap bootstrap) {
//        this.bootstrap = bootstrap;
//    }
//
//
////    @Override
////    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//////        ctx.writeAndFlush(new Date() + "hello server");
////        System.out.println(new Date() + ":客户端开始登录");
////
////        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
////        loginRequestPacket.setUserId(UUID.randomUUID().toString());
////        loginRequestPacket.setUsername("qehing");
////        loginRequestPacket.setPassword("pwd");
////
////        ByteBuf bytebuf = PacketCodeC.encode(ctx.alloc().ioBuffer(), loginRequestPacket);
////        ctx.channel().writeAndFlush(bytebuf);
////    }
////
////    @Override
////    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
////        ByteBuf responseByteBuf = (ByteBuf) msg;
////        Packet packet = PacketCodeC.decode(responseByteBuf);
////        if (packet instanceof LoginResponsePacket) {
////            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
////            if (loginResponsePacket.getSuccess()){
////                LoginUtil.markLogin(ctx.channel());
////                System.out.println("login success");
////            } else {
////                System.out.println("login failed, "+loginResponsePacket.getReason());
////            }
////        } else if (packet instanceof MessageResponsePacket messageResponsePacket) {
////            System.out.println(new Date() + ":" +messageResponsePacket.getMessage());
////        }
//////        System.out.println(bytebuf);
//////        ctx.channel().close();
////    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        ctx.channel().eventLoop().schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.err.println("服务端链接不上，开始重连操作...");
//                try {
//                    NettyRemotingClient.connect(bootstrap, 10);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 3, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        System.err.println(cause.getMessage());
////        cause.printStackTrace();
//        ctx.close();
//    }
//}
