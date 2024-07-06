package com.qehing.client.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.response.QuitGroupResponsePacket;

public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if (msg.getSuccess()){
            System.out.println("退出群聊["+msg.getGroupId()+"]成功");
        } else {
            System.out.println("退出群聊["+msg.getGroupId()+"]失败");
        }
    }
}
