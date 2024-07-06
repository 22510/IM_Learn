package com.qehing.server.handlerService;

import com.qehing.dbService.MyBatisInit;
import com.qehing.dbService.dao.User;
import com.qehing.dbService.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

public class UserService {

    public static Boolean register(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        SqlSession sqlSession = MyBatisInit.getSqlSession().openSession(true);
        try {
            return sqlSession.getMapper(UserMapper.class).insertUser(user) != 0 ;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return false;
    }

    public static Boolean login(String email, String password) {
        SqlSession sqlSession = MyBatisInit.getSqlSession().openSession(true);
        try {
            User user = sqlSession.getMapper(UserMapper.class).selectByEmail(email);
            if (user.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return false;
    }

    public static User getUser(String email) {
        SqlSession sqlSession = MyBatisInit.getSqlSession().openSession(true);
        User user =  sqlSession.getMapper(UserMapper.class).selectByEmail(email);
        sqlSession.close();
        return user;
    }
}
