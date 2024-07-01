package com.qehing;

import java.io.IOException;
import java.net.ServerSocket;

public class FreePortFinder {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(0);
        System.out.println(socket.getLocalPort());
    }
}
