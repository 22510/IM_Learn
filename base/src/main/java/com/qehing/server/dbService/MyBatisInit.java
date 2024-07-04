package com.qehing.server.dbService;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MyBatisInit {

//    public static final MyBatisInit INSTANCE = new MyBatisInit();
    public static SqlSessionFactory sqlSessionFactory;

//    private MyBatisInit() {
    static {
        String resource = "mybatis-config.xml";
        //将XML配置文件构建为Configuration配置类
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            // 通过加载配置文件流构建一个SqlSessionFactory  DefaultSqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSession() {
        return sqlSessionFactory;
    }
}
