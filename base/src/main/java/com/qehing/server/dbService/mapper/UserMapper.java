package com.qehing.server.dbService.mapper;

import com.qehing.server.dbService.dao.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();
    User selectById(@Param("user_id") Long userId);
    User selectByEmail(@Param("email") String email);
    Boolean register(User user);
    Boolean login(@Param("email") String email, @Param("password") String password);
    Boolean delete(@Param("user_id") Long userId);

}
