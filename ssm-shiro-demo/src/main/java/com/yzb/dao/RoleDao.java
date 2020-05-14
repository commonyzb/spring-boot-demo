package com.yzb.dao;

import com.yzb.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@Mapper
public interface RoleDao {

    int saveRole(Role role);

    int deleteRoleById(int id);

    int updateRole(Role role);

    Role getRoleById(int id);

    @Select("SELECT r.* FROM ssm.user u INNER JOIN user_role ur ON u.id = ur.user_id INNER JOIN role r ON ur.role_id = r.id WHERE u.id=#{id} ")
    List<Role> getRolesByUserId(int id);

    @Select(" SELECT r.* FROM ssm.user u INNER JOIN user_role ur ON u.id = ur.user_id INNER JOIN role r ON ur.role_id = r.id WHERE u.user_name=#{userName} ")
    List<Role> getRolesByUserName(String userName);

    @Select(" SELECT r.role_name FROM ssm.user u INNER JOIN user_role ur ON u.id = ur.user_id INNER JOIN role r ON ur.role_id = r.id WHERE u.user_name=#{userName} ")
    Set<String> getRoleNamesByUserName(String userName);

}
