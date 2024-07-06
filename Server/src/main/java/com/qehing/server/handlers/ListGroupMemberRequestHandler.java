package com.qehing.server.handlers;


import com.qehing.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocols.request.ListGroupMembersRequestPacket;
import protocols.response.ListGroupMembersResponsePacket;
import session.Session;

import java.util.ArrayList;
import java.util.List;

public class ListGroupMemberRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMemberRequestHandler INSTANCE = new ListGroupMemberRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        List<Session> sessionList = new ArrayList<Session>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        ListGroupMembersResponsePacket response = new ListGroupMembersResponsePacket();
        response.setGroupId(groupId);
        response.setSessionList(sessionList);
        ctx.writeAndFlush(response);
    }
}
