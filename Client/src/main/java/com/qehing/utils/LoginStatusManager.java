package com.qehing.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginStatusManager {

    private static final String LOGIN_STATUS_FILE = "login_status.properties";

    public static void saveLoginStatus(String email, String authToken) {
        Properties properties = new Properties();
        properties.setProperty("email", email);
        properties.setProperty("authToken", authToken);
        
        try (FileOutputStream out = new FileOutputStream(LOGIN_STATUS_FILE)) {
            properties.store(out, "User Login Status");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] loadLoginStatus() {
        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream(LOGIN_STATUS_FILE)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String username = properties.getProperty("email");
        String authToken = properties.getProperty("authToken");

        return new String[] { username, authToken };
    }
}