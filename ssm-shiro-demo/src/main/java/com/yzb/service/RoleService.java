package com.yzb.service;

import com.yzb.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    int saveRole(Role role);

    int deleteRoleById(int id);

    int updateRole(Role role);

    Role getRoleById(int id);

    List<Role> getRolesByUserId(int id);

    List<Role> getRolesByUserName(String userName);

    Set<String> getRoleNamesByUserName(String userName);
}
