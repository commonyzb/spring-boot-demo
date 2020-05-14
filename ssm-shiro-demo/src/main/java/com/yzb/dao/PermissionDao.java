package com.yzb.dao;

import com.yzb.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@Mapper
public interface PermissionDao {

    int savePermission(Permission permission);

    int deletePermissionById(int id);

    int updatePermission(Permission permission);

    Permission getPermissionById(int id);

    List<Permission> getPermissionsByRoleId(int id);

    List<Permission> getPermissionsByRoleName(String roleName);

    @Select(" SELECT p.permission_name FROM permission p INNER JOIN role_permission rp ON p.id = rp.permission_id INNER JOIN role r ON rp.role_id = r.id WHERE role_name=#{roleName} ")
    Set<String> getPermissionNamesByRoleName(String roleName);

    List<Permission> getPermissionsByUserId(int id);

    List<Permission> getPermissionsByUserName(String userName);

    @Select("SELECT p.permission_name FROM permission p INNER JOIN role_permission rp ON p.id = rp.permission_id INNER JOIN role r ON rp.role_id = r.id INNER JOIN user_role ur ON r.id = ur.role_id INNER JOIN user u ON ur.user_id = u.id WHERE u.user_name=#{userName} ")
    Set<String> getPermissionNamesByUserName(String userName);

}
