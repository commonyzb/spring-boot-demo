package com.yzb.service;

import com.yzb.model.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    int savePermission(Permission permission);

    int deletePermissionById(int id);

    int updatePermission(Permission permission);

    Permission getPermissionById(int id);

    List<Permission> getPermissionsByRoleId(int id);

    List<Permission> getPermissionsByRoleName(String roleName);

    List<Permission> getPermissionsByUserId(int id);

    List<Permission> getPermissionsByUserName(String userName);

    Set<String> getPermissionNamesByUserName(String userName);

}
