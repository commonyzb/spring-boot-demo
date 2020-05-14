package com.yzb.service.impl;

import com.yzb.model.Role;
import com.yzb.dao.RoleDao;
import com.yzb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public int saveRole(Role role) {
        return roleDao.saveRole(role);
    }

    @Override
    public int deleteRoleById(int id) {
        return roleDao.deleteRoleById(id);
    }

    @Override
    public int updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public List<Role> getRolesByUserId(int id) {
        return roleDao.getRolesByUserId(id);
    }

    @Override
    public List<Role> getRolesByUserName(String userName) {
        return roleDao.getRolesByUserName(userName);
    }

    @Override
    public Set<String> getRoleNamesByUserName(String userName) {
        return roleDao.getRoleNamesByUserName(userName);
    }

}
