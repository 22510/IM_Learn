package com.qehing.dbService.dao;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Friend {
    private Long id;
    private Long friendId;
    private Timestamp createdAt;
}
