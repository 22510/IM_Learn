package com.qehing.dbService.mapper;

import com.qehing.dbService.dao.TestDao;

public interface TestDaoMapper {
    TestDao selectTest(Integer id);

    Boolean addTest(TestDao test);

    Boolean deleteTest(String name);
}
