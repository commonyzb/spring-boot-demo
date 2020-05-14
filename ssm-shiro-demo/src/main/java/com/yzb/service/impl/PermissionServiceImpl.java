package com.yzb.service.impl;

import com.yzb.model.Permission;
import com.yzb.dao.PermissionDao;
import com.yzb.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public int savePermission(Permission permission) {
        return permissionDao.savePermission(permission);
    }

    @Override
    public int deletePermissionById(int id) {
        return permissionDao.deletePermissionById(id);
    }

    @Override
    public int updatePermission(Permission permission) {
        return permissionDao.updatePermission(permission);
    }

    @Override
    public Permission getPermissionById(int id) {
        return permissionDao.getPermissionById(id);
    }

    @Override
    public List<Permission> getPermissionsByRoleId(int id) {
        return permissionDao.getPermissionsByRoleId(id);
    }

    @Override
    public List<Permission> getPermissionsByRoleName(String roleName) {
        return permissionDao.getPermissionsByRoleName(roleName);
    }

    @Override
    public List<Permission> getPermissionsByUserId(int id) {
        return permissionDao.getPermissionsByUserId(id);
    }

    @Override
    public List<Permission> getPermissionsByUserName(String userName) {
        return permissionDao.getPermissionsByUserName(userName);
    }

    @Override
    public Set<String> getPermissionNamesByUserName(String userName) {
        return permissionDao.getPermissionNamesByUserName(userName);
    }

}
