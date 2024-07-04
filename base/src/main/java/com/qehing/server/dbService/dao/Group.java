package com.qehing.server.dbService.dao;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Group {
    private Long groupId;
    private String groupName;
    private Timestamp createAt;
    private Timestamp updateAt;
}
