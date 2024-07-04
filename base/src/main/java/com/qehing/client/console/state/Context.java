package com.qehing.client.console.state;

import io.netty.channel.Channel;
import lombok.Data;

import java.util.Scanner;

@Data
public class Context {
    private State state;
    private boolean running = true;
    private Channel channel;

    public Context(Channel channel) {
        state = MainMenuState.INSTANCE;  // 初始状态为主菜单
        this.channel = channel;
    }

    public void show() {
        state.show();
    }

    public void handlerExec(Scanner scanner) {
        state.handlerExec(scanner, this);
    }

    public boolean isRunning() {
        return running;
    }

    public void exit() {
        running = false;
    }
}
