package com.qehing.server.dbService.dao;

import lombok.Data;

@Data
public class TestDao {
    private int id;
    private String name;
    private String password;
    private int testId;

    @Override
    public String toString() {
        return "TestDao [id=" + id + ", name=" + name + ", password=" + password + "]";
    }
}
