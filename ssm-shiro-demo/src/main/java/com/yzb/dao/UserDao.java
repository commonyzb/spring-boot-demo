package com.yzb.dao;

import com.yzb.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert(" INSERT INTO user(user_name,password,status) VALUES(#{userName},#{password},#{status}) ")
    int saveUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUserById(int id);

    @Update(" UPDATE user SET user_name=#{userName},password=#{password},status=#{status} ")
    int updateUser(User user);

    @Select("SELECT * FROM user WHERE id = #{id} ")
    User getUserById(int id);

    @Select("SELECT * FROM user WHERE user_name = #{userName}")
    User getUserByName(String userName);

    @Select("SELECT * FROM user")
    List<User> listUser();

}
