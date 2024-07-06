package com.qehing.console.state;

import io.netty.channel.Channel;
import lombok.Data;
import lombok.Getter;

import java.util.Scanner;

@Data
public class Context {
    private State state;

    @Getter
    private boolean running = true;

    private Channel channel;

    public Context(Channel channel) {
        state = BeginMenuState.INSTANCE;  // 初始状态为主菜单
        this.channel = channel;
    }

    public void show() {
        state.show();
    }

    public void handlerExec(Scanner scanner) {
        state.handlerExec(scanner, this);
    }

    public void exit() {
        running = false;
    }
}
