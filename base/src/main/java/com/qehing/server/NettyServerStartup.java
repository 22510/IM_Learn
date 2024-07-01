package com.qehing.server;

public class NettyServerStartup {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> new NettyRemotingServer().start()).start();
    }
}
