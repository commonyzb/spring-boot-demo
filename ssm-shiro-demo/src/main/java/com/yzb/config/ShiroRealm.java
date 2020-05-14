package com.yzb.config;

import com.yzb.model.User;
import com.yzb.service.PermissionService;
import com.yzb.service.RoleService;
import com.yzb.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private RoleService roleService;
    @Autowired
    @Lazy
    private PermissionService permissionService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.getUserByName(userName);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roleNames = roleService.getRoleNamesByUserName(userName);
        authorizationInfo.addRoles(roleNames);
        Set<String> permissionNames = permissionService.getPermissionNamesByUserName(userName);
        authorizationInfo.addStringPermissions(permissionNames);
        return authorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String) authenticationToken.getPrincipal();
        User currentUser = userService.getUserByName(userName);
        if (currentUser == null){
            throw new UnknownAccountException();
        }
        if (currentUser.getStatus() != 0){
            throw new LockedAccountException();
        }
        System.out.println(currentUser.toString());
        System.out.println(this.getName());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                currentUser.getUserName(),
                currentUser.getPassword(),
                getName()
        );
        return authenticationInfo;
    }

}
