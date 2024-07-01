package com.qehing.utils;


import io.netty.channel.Channel;

public class LoginUtil {
    public static void markLogin(Channel channel) {
        System.out.println("通道为："+channel);
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean isLogin(Channel channel) {
        System.out.println("通道为："+channel);
        return channel.attr(Attributes.LOGIN).get() != null;
    }
}
