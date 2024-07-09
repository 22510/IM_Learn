package com.qehing.client;

public class NettyClientStartup {
    public static void main(String[] args) {
//        new Thread(() -> new NettyRemotingClient().start()).start();
        new NettyRemotingClient().start();
        System.out.println("test merger dev");
        System.out.println("test merger: dev wait for feature");
    }
}
