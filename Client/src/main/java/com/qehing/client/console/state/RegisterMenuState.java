package com.qehing.client.console.state;

import com.qehing.client.console.command.*;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegisterMenuState implements State{

    public static final RegisterMenuState INSTANCE = new RegisterMenuState();

    private final Map<String, ConsoleCommand> mainMenuMap;
    private final Map<String, State> stateMap;
    private Channel channel;

    public RegisterMenuState() {
        mainMenuMap = new HashMap<>();

        mainMenuMap.put("sendToUser", new SendToUserConsoleCommand());
        mainMenuMap.put("logout", new LogoutConsoleCommand());
        mainMenuMap.put("createGroup", new CreateGroupConsoleCommand());
        mainMenuMap.put("joinGroup", new JoinGroupConsoleCommand());
        mainMenuMap.put("quitGroup", new QuitGroupConsoleCommand());
        mainMenuMap.put("listGroup", new ListGroupMembersConsoleCommand());
        mainMenuMap.put("sendToGroup", new SendToGroupConsoleCommand());


        stateMap = new HashMap<>();
        stateMap.put("login", this);
    }

    @Override
    public void show() {
        System.out.println("---IM_SYS Main Menu---");
        System.out.println("Login your account:{login}");
        System.out.println("Register your account:{register}");
        System.out.println("Get some help:{help}");
        System.out.println("Exit:{exit}");
    }

    @Override
    public void handlerExec(Scanner scanner, Context context) {
//        String command = scanner.next();
//
//        if (!SessionUtil.hasLogin(channel)) {
//            return;
//        }
//
//        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
//        if (consoleCommand != null) {
//            consoleCommand.exec(scanner, channel);
//        } else {
//            System.err.println("无法识别["+command+"]指令");
//        }
    }

//    @Override
//    public void exec(Scanner scanner, Channel channel) {
//
//    }
}
