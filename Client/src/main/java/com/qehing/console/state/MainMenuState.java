package com.qehing.console.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenuState implements State {

    public static final MainMenuState INSTANCE = new MainMenuState();

    private final Map<String, State> stateMap;

    private MainMenuState() {
        stateMap = new HashMap<>();

    }

    @Override
    public void show() {
        System.out.println("---IM_SYS<Main>Menu---");
        System.out.println("Add friend:{add_friend}");
        System.out.println("My friends:{friends}");
        System.out.println("Send message to friend:{send2friend}");
        System.out.println("Add groups:{add_group}");
        System.out.println("My groups:{groups}");
        System.out.println("Send message to group:{send2group}");
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
