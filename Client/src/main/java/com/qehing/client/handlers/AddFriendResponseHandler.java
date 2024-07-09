package com.qehing.client.handlers;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.response.AddFriendResponsePacket;

public class AddFriendResponseHandler extends SimpleChannelInboundHandler<AddFriendResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendResponsePacket msg) throws Exception {
        if (msg.getSuccess()){
            System.out.println("对用户["+msg.getFriendEmail()+"]的好友申请成功发出，请等待对方通过申请。");
        } else {
            System.out.println("对用户["+msg.getFriendEmail()+"]的好友申请发送失败，"+msg.getReason());
        }
    }
}
