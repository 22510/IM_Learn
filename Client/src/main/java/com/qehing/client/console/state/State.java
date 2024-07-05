package com.qehing.client.console.state;


import java.util.Scanner;

public interface State {
    void show();
    void handlerExec(Scanner scanner, Context context);
}
