package com.qehing.server.handlers;

import com.qehing.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocols.request.GroupMessageRequestPacket;
import protocols.response.GroupMessageResponsePacket;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        String groupId = msg.getToGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromGroupId(groupId);
        groupMessageResponsePacket.setMessage(msg.getMessage());
        groupMessageResponsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));

        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
