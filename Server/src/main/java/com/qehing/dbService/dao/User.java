package com.qehing.dbService.dao;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Long userId;
    // TODO: 考虑是否切换雪花算法生成ID
    private String username;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
