package com.qehing.server.dbService.mapper;

import com.qehing.server.dbService.dao.TestDao;

public interface TestDaoMapper {
    TestDao selectTest(Integer id);

    Boolean addTest(TestDao test);

    Boolean deleteTest(String name);
}
