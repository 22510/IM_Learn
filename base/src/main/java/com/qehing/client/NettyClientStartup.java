package com.qehing.client;

public class NettyClientStartup {
    public static void main(String[] args) {
        new Thread(() -> new NettyRemotingClient().start()).start();
    }
}
