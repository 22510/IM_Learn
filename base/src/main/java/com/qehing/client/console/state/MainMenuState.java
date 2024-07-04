package com.qehing.client.console.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenuState implements State{

    public static final MainMenuState INSTANCE = new MainMenuState();

    private final Map<String, State> stateMap;

    private MainMenuState() {
        stateMap = new HashMap<>();
        stateMap.put("login", LoginMenuState.INSTANCE);
        stateMap.put("register", RegisterMenuState.INSTANCE);
        stateMap.put("help", HelpMenuState.INSTANCE);
    }

    @Override
    public void show() {
        System.out.println("---IM_SYS<Main>Menu---");
        System.out.println("Login your account:{login}");
        System.out.println("Register your account:{register}");
        System.out.println("Get some help:{help}");
        System.out.println("Exit:{exit}");
    }

    @Override
    public void handlerExec(Scanner scanner, Context context) {
        String input = scanner.next();

        State nextState = stateMap.get(input);
        if (nextState != null) {
            context.setState(nextState);
        }else {
            System.err.println("Invalid state.");
        }
    }
}
