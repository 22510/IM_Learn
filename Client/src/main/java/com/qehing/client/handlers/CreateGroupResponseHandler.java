package com.qehing.client.handlers;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.response.CreateGroupResponsePacket;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.println("群聊创建成功，群ID："+msg.getGroupId());
        System.out.println("群聊创建成功，群成员："+msg.getUserNameList());
    }
}
