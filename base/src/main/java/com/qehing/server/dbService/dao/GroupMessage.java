package com.qehing.server.dbService.dao;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GroupMessage {
    private Long messageId;
    private Long groupId;
    private Long senderId;
    private String content;
    private Timestamp timestamp;
}
