package com.qehing.dbService.dao;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserMessage {
    private Long messageId;
    private Long senderId;
    private Long receiverId;
    private String content;
    private Timestamp time;
}
