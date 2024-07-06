package com.qehing.client.handlers;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.response.RegisterResponsePacket;

public class RegisterResponseHandler extends SimpleChannelInboundHandler<RegisterResponsePacket> {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) {
//        System.out.println(new Date() + ":客户端开始登录");
//
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setUsername("qehing");
//        loginRequestPacket.setPassword("pwd");
////        ByteBuf bytebuf = PacketCodeC.encode(ctx.alloc().ioBuffer(), loginRequestPacket);
//        ctx.channel().writeAndFlush(loginRequestPacket);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterResponsePacket registerResponsePacket) throws Exception {
        String userEmail = registerResponsePacket.getEmail();

        if (registerResponsePacket.isSuccess()) {
            System.out.println("[" + userEmail + "]注册成功");
            // TODO：不使用SessionUtil来缓存登录状态
        } else {
            System.out.println("[" + userEmail + "]注册失败，原因：" + registerResponsePacket.getReason());
        }

//        if (){
//            System.out.println("登录成功");
//            System.out.println("通道为："+ctx.channel());
//            LoginUtil.markLogin(ctx.channel());
//        } else {
//            System.out.println("登录失败");
//        }
    }

//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) {
//        System.out.println("客户端连接被关闭!");
//    }
}
