package com.qehing.utils;


import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import session.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session sessionId, Channel channel) {
//        System.out.println("保存用户["+sessionId.getUserId()+"]channel");
        userIdChannelMap.put(sessionId.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(sessionId);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)){
            Session session = getSession(channel);
            userIdChannelMap.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(session + " 退出登录");
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }

    public static void bindGroupSession(String groupId, ChannelGroup channelGroup) {
        System.out.println("保存群组["+groupId+"]channelGroup");
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }
}
