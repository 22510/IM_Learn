package com.qehing.client.handlers;


import com.qehing.client.ClientConstant;
import com.qehing.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.response.LoginResponsePacket;
import session.Session;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

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
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getEmail();

        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + loginResponsePacket.getUserId());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
            ClientConstant.IS_LOGIN = true;
            // TODO：不使用SessionUtil来缓存登录状态
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
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
