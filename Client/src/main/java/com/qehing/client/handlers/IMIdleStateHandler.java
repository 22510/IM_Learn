package com.qehing.client.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        // 读空闲时间、写空闲时间、所有类型的空闲时间、时间单位
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }
//111
    /**
     * 当通道空闲时触发
     */
    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        // 关闭该通道，减少资源消耗。
        ctx.channel().close();
    }

//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("与客户端连接断开-IMIdleStateHandler");
////        super.channelInactive(ctx);
////        ctx.channel().close();
//        // 事件在pipeline中按照Handler添加的顺序被处理，其中不同的事件触发handler的对应处理方法
//        // 当事件匹配到某个handler的处理方法，但又不进行传播时，该事件只会在pipeline处理一次。
//    }
}