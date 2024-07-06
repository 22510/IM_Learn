package com.qehing.server.handlers;


import com.qehing.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import protocols.request.CreateGroupRequestPacket;
import protocols.response.CreateGroupResponsePacket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        System.out.println("服务器处理群聊创建");
        List<String> userIdList = msg.getUserIdList();
        List<String> userNameList = new ArrayList<>();

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setSuccess(true);
        String groupId = randomGroupId();
        responsePacket.setGroupId(groupId);
        responsePacket.setUserNameList(userNameList);

        SessionUtil.bindGroupSession(groupId,channelGroup);

        channelGroup.writeAndFlush(responsePacket);
        System.out.println("创建群聊成功，群id:"+responsePacket.getGroupId());
        System.out.println("群成员:"+responsePacket.getUserNameList());
    }

    private static String randomGroupId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
