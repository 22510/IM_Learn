package com.qehing.dbService;

import com.qehing.dbService.mapper.TestDaoMapper;
import com.qehing.dbService.dao.TestDao;
import org.apache.ibatis.session.SqlSession;

public class TestDaoMain {
    public static void main(String[] args){
//        String resource = "mybatis-config.xml";
        //            //将XML配置文件构建为Configuration配置类
//            Reader reader = Resources.getResourceAsReader(resource);
//            // 通过加载配置文件流构建一个SqlSessionFactory  DefaultSqlSessionFactory
//            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
//
//            // 数据源 执行器  DefaultSqlSession
//            SqlSession session = sqlMapper.openSession();

        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisInit.getSqlSession().openSession(true);
            TestDaoMapper mapper = sqlSession.getMapper(TestDaoMapper.class);
            System.out.println(mapper.getClass());
            TestDao user = mapper.selectTest(1);
            System.out.println(user);

            TestDao addtest = new TestDao();
//                addtest.setId(3);
            addtest.setName("c");
            addtest.setPassword("c1");
            addtest.setTestId(3);
            Boolean b = mapper.addTest(addtest);
            System.out.println(b);
            System.out.println(addtest.getId());


            TestDao user1 = mapper.selectTest(1);
            System.out.println(user1);

            Boolean delete = mapper.deleteTest("a");
            System.out.println("delete: " + delete);


//            sqlSession.commit();
//            TODO: HikariCP线程池集成

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
