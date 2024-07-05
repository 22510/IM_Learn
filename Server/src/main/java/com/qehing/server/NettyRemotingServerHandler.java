//package com.qehing.server;
//
//import com.qehing.protocols.*;
//import com.qehing.protocols.request.LoginRequestPacket;
//import com.qehing.protocols.request.MessageRequestPacket;
//import com.qehing.protocols.response.LoginResponsePacket;
//import com.qehing.protocols.response.MessageResponsePacket;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelId;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.channel.group.ChannelGroup;
//import io.netty.channel.group.DefaultChannelGroup;
//import io.netty.util.concurrent.GlobalEventExecutor;
//
//import java.util.Date;
//
//public class NettyRemotingServerHandler extends ChannelInboundHandlerAdapter {
//    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//    private static ChannelId channelId;
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("连接成功");
////        System.out.println(ctx.channel().remoteAddress().toString());
////        System.out.println("通道ID"+ctx.channel().id().toString());
////        channelId = ctx.channel().id();
////        channels.add(ctx.channel());
////        System.out.println("Server channel active...");
////        Thread.sleep(5000);
////        System.out.println("发送开始");
////        for (int i = 0; i < 10; i++) {
////            System.out.print(i);
////            NettyRemotingServerHandler.broadcast(String.valueOf(i));
////        }
////        System.out.println();
////        NettyRemotingServerHandler.broadcast("close channel");
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
////        System.out.println("Server received: " + msg);
////        ByteBuf buf = Unpooled.buffer();
////        buf.writeBytes("server to client".getBytes());
////        ctx.writeAndFlush(buf);
//        ByteBuf requestByteBuf = (ByteBuf) msg;
//        Packet packet = PacketCodeC.decode(requestByteBuf);
//        if (packet instanceof LoginRequestPacket) {
//            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
//
//            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//            loginResponsePacket.setVersion(packet.getVersion());
//
//            if (valid(loginRequestPacket)){
//                System.out.println("login success");
//                loginResponsePacket.setSuccess(true);
////                ctx.channel().writeAndFlush("login success");
//            } else {
//                System.out.println("login failed");
//                loginResponsePacket.setSuccess(false);
//                loginResponsePacket.setReason("账号密码校验失败");
////                ctx.channel().writeAndFlush("login failed");
//            }
//
//            ByteBuf responseByteBuf = PacketCodeC.encode(ctx.alloc().ioBuffer(),loginResponsePacket);
//            ctx.channel().writeAndFlush(responseByteBuf);
//        } else if (packet instanceof MessageRequestPacket) {
//            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
//            System.out.println(new Date() + ":收到客户端新消息："+messageRequestPacket.getMessage());
//
//            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
//            messageResponsePacket.setVersion(messageRequestPacket.getVersion());
//            messageResponsePacket.setMessage("服务端回应："+messageRequestPacket.getMessage());
//            ByteBuf byteBuf = PacketCodeC.encode(ctx.alloc().ioBuffer(),messageResponsePacket);
//            ctx.channel().writeAndFlush(byteBuf);
//        }
//    }
//
//    private boolean valid(LoginRequestPacket loginRequestPacket) {
//        return true;
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//
//    // 主动向所有客户端发送消息
//    public static void broadcast(String message) {
////        channels.find(channelId).writeAndFlush(message);
//        channels.writeAndFlush(message);
//    }
//}
