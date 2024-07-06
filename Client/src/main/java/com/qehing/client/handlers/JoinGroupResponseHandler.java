package com.qehing.client.handlers;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.response.JoinGroupResponsePacket;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if (msg.getSuccess()){
            System.out.println("加入群聊["+msg.getGroupId()+"]成功");
        } else {
            System.out.println("加入群聊["+msg.getGroupId()+"]失败");
        }
    }
}
