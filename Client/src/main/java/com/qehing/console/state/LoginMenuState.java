package com.qehing.console.state;

import com.qehing.console.command.ConsoleCommand;
import io.netty.channel.Channel;
import protocols.request.LoginRequestPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginMenuState implements State, ConsoleCommand {

    public static final LoginMenuState INSTANCE = new LoginMenuState();

    private final Map<String, State> stateMap;

    private LoginMenuState() {
        stateMap = new HashMap<>();
        stateMap.put("register", RegisterMenuState.INSTANCE);
        stateMap.put("back", BeginMenuState.INSTANCE);
    }

    @Override
    public void show() {
        System.out.println("---IM_SYS<Login>Menu---");
        System.out.println("Sure to Login:{sure}");
        System.out.println("Register your account:{register}");
        System.out.println("Back to Main menu:{back}");
        System.out.println("Exit:{exit}");
    }

    @Override
    public void handlerExec(Scanner scanner, Context context) {
        String input = scanner.next();
        if (input.equals("exit")) {
            context.exit();
            return;
        }

        State nextState = stateMap.get(input);
        if (nextState != null) {
            context.setState(nextState);
        } else if (input.equals("sure")){
            exec(scanner, context.getChannel());
            context.setState(MainMenuState.INSTANCE);
        } else {
            System.err.println("Invalid input");
        }

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.print(">UserEmail: ");
        loginRequestPacket.setEmail(scanner.next());
        System.out.print(">Password: ");
        loginRequestPacket.setPassword(scanner.next());
        System.out.println("Waiting login...");
        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
        // TODO: 这里的流程如何设计？登录后获取记录，或者其他情况？
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
    }
}
