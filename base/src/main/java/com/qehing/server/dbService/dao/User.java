package com.qehing.server.dbService.dao;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
