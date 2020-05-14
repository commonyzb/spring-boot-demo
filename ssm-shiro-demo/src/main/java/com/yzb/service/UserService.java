package com.yzb.service;

import com.yzb.model.User;

import java.util.List;

public interface UserService {

    int saveUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);

    User getUserById(int id);

    User getUserByName(String userName);

    List<User> listUser();

}
