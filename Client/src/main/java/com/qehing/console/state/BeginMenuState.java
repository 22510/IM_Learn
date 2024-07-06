package com.qehing.console.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BeginMenuState implements State {

    public static final BeginMenuState INSTANCE = new BeginMenuState();

    private final Map<String, State> stateMap;

    private BeginMenuState() {
        stateMap = new HashMap<>();
        stateMap.put("login", LoginMenuState.INSTANCE);
        stateMap.put("register", RegisterMenuState.INSTANCE);
        stateMap.put("help", HelpMenuState.INSTANCE);
        // TODO: 能不能写到配置文件里，类似MyBatis那样的？
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
