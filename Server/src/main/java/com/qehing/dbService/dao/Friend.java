package com.qehing.dbService.dao;

import lombok.Data;

@Data
public class Friend {
    private Long userId;
    private Long friendId;
    private Boolean apply;
}
