package com.qehing.server.dbService.dao;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GroupsRelate {
    private Long groupId;
    private Long userId;
    private Timestamp createAt;
}
