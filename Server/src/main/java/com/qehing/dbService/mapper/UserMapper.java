package com.qehing.dbService.mapper;

import com.qehing.dbService.dao.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();
    User selectById(@Param("user_id") Long userId);
    User selectByEmail(@Param("email") String email);
    Integer insertUser(User user);
//    Boolean delete(@Param("user_id") Long userId);

}
