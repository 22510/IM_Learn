package com.qehing.dbService;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteInit {
    private static final String BASE_URL = "jdbc:sqlite:";

//    public static void Init() throws SQLException {
//        System.out.println("SQLite init started");
//        DriverManager.getConnection(URL);
//    }

    public static void init(String email) {
        try {
            String URL = BASE_URL + email + ".db";
            System.out.println(URL);
            DriverManager.getConnection(URL);
            System.out.println("SQLite init started");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
//        String email = "2980267045@qq.com";
////        System.out.println(removeAfterAtSymbol(email));
//        SQLiteInit.init(email);

        File currentDirectory = new File(System.getProperty("user.dir"));
        System.out.println("Current directory: " + currentDirectory);

        // 获取当前目录下的所有文件
        File[] files = currentDirectory.listFiles();
        System.out.println(files.length);

        // 检查是否存在以 .db 结尾的文件
        boolean foundDbFile = false;
        if (files != null) {
            for (File file : files) {
                if (file.isFile()){
                    System.out.println("Found file: " + file);
                } else if (file.isDirectory()) {
                    System.out.println("Found directory: " + file);
                }
                if (file.isFile() && file.getName().endsWith(".db")) {
                    foundDbFile = true;
                    break;
                }
            }
        }

        // 输出结果
        if (foundDbFile) {
            System.out.println("Found a .db file in the current directory.");
        } else {
            System.out.println("No .db file found in the current directory.");
        }
    }

//    private static String removeAfterAtSymbol(String input) {
//        int atIndex = input.indexOf('@');
//        if (atIndex != -1) {
//            return input.substring(0, atIndex);
//        }
//        return input;  // 如果没有找到'@'字符，返回原字符串
//    }

}
