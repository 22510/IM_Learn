package com.qehing.client;

public class NettyClientStartup {
    public static void main(String[] args) {
//        new Thread(() -> new NettyRemotingClient().start()).start();
        new NettyRemotingClient().start();
        System.out.println("new git branch <feature> from dev");
        System.out.println("test merge: feature2dev");
    }
}
