package com.qehing.console.state;

import com.qehing.console.command.ConsoleCommand;
import io.netty.channel.Channel;
import protocols.request.RegisterRequestPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegisterMenuState implements State, ConsoleCommand {

    public static final RegisterMenuState INSTANCE = new RegisterMenuState();

    private final Map<String, State> stateMap;

    public RegisterMenuState() {
        stateMap = new HashMap<>();

        stateMap.put("login", LoginMenuState.INSTANCE);
        stateMap.put("back", BeginMenuState.INSTANCE);
    }

    @Override
    public void show() {
        System.out.println("---IM_SYS<Register>Menu---");
        System.out.println("Sure to Register:{sure}");
        System.out.println("Login your account:{login}");
        System.out.println("Back to Main menu:{back}");
        System.out.println("Exit:{exit}");
    }

    @Override
    public void handlerExec(Scanner scanner, Context context) {
        String input = scanner.next();
        if (input.equals("exit")) {
            context.exit();
        }

        State nextState = stateMap.get(input);
        if (nextState != null) {
            context.setState(nextState);
        } else if (input.equals("sure")){
            exec(scanner, context.getChannel());
            context.setState(BeginMenuState.INSTANCE);
        } else {
            System.err.println("Invalid input");
        }

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        RegisterRequestPacket registerRequestPacket = new RegisterRequestPacket();
        System.out.print(">UserEmail: ");
        registerRequestPacket.setEmail(scanner.next());
        System.out.print(">Password: ");
        registerRequestPacket.setPassword(scanner.next());
        System.out.println("Waiting login...");
        // 发送登录数据包
        channel.writeAndFlush(registerRequestPacket);
        waitForRegisterResponse();
    }

    private static void waitForRegisterResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
