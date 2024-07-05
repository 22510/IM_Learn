package com.qehing.server.handlers;

import com.qehing.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    public AuthHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if (!LoginUtil.isLogin(ctx.channel())){
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            System.out.println("登录验证通过，移出登录校验器");
            ctx.pipeline().remove(this);
            System.out.println("执行下一个处理流程");
            super.channelRead(ctx, msg);
        }
    }

//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        if (LoginUtil.isLogin(ctx.channel())){
//            System.out.println("登录验证完毕，后续无需登录，移出该验证器");
//        } else {
//            System.out.println("无登录验证，强制关闭连接");
//        }
//    }
}
