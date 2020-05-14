package com.yzb.controller;

import com.yzb.common.CommonResponse;
import com.yzb.model.User;
import com.yzb.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @ResponseBody
    @PostMapping("/user")
    public CommonResponse saveUser(@RequestBody User user, HttpServletResponse response){
        CommonResponse<User> commonResponse = new CommonResponse<User>();
        if(user != null){
            if (1 == userService.saveUser(user)){
                response.setStatus(201);
                response.setContentType("application/json");
                commonResponse.setCode(0);
                commonResponse.setMessage("添加用户成功！");
                commonResponse.setData(user);
            }
            else {
                response.setStatus(500);
                response.setContentType("application/json");
                commonResponse.setCode(1);
                commonResponse.setMessage("添加用户失败！");
            }
        }
        return commonResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public CommonResponse deleteUser(@PathVariable int id, HttpServletResponse response){
        CommonResponse commonResponse = new CommonResponse();
        if (1 == userService.deleteUserById(id)){
            response.setStatus(204);
            response.setContentType("application/json");
            //下边两行并不必要，因为 HTTP 204 响应码默认不返回实体内容，只包含请求头和状态行，省略了多余的数据传输。
            commonResponse.setCode(0);
            commonResponse.setMessage("删除用户成功！");
        }
        else {
            response.setStatus(500);
            response.setContentType("application/json");
            commonResponse.setCode(1);
            commonResponse.setMessage("删除用户失败！");
        }
        return commonResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public CommonResponse updateUser(@RequestBody User user, HttpServletResponse response){
        CommonResponse<User> commonResponse = new CommonResponse<User>();
        if (1 == userService.updateUser(user)){
            response.setStatus(201);
            response.setContentType("application/json");
            commonResponse.setCode(0);
            commonResponse.setMessage("更新用户信息成功！");
            commonResponse.setData(user);
        }
        else {
            response.setStatus(500);
            response.setContentType("application/json");
            commonResponse.setCode(1);
            commonResponse.setMessage("更新用户信息失败！");
        }
        return commonResponse;
    }

    @RequiresPermissions("user:view")
    @ResponseBody
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public CommonResponse getUserById(@PathVariable int id, HttpServletResponse response){
        CommonResponse<User> commonResponse = new CommonResponse<User>();
        User user = userService.getUserById(id);
        if (user != null){
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            commonResponse.setCode(0);
            commonResponse.setMessage("获取用户信息成功！");
            commonResponse.setData(user);
        }
        else {
            response.setStatus(500);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            commonResponse.setCode(1);
            commonResponse.setMessage("获取用户信息失败，用户可能不存在！");
        }
        return commonResponse;
    }

    @RequiresPermissions("user:view")
    @ResponseBody
    @RequestMapping("/users")
    public CommonResponse listUser(HttpServletResponse response){
        CommonResponse<List<User>> commonResponse = new CommonResponse<List<User>>();
        List<User> users = userService.listUser();
        if (users.isEmpty() == false){
            commonResponse.setCode(0);
            commonResponse.setMessage("获取用户信息成功！");
            commonResponse.setData(users);
            response.setStatus(200);
        }
        else {
            commonResponse.setCode(1);
            commonResponse.setMessage("获取用户信息失败！");
            commonResponse.setData(users);
            response.setStatus(200);
        }
        return commonResponse;
    }

    @ResponseBody
    @RequestMapping("/user/login")
    public CommonResponse userLogin(@RequestBody User user){
        CommonResponse commonResponse = new CommonResponse();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
            commonResponse.setCode(0);
            commonResponse.setMessage("登陆成功！");
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(1);
            commonResponse.setMessage(e.toString());
        }
        return commonResponse;
    }

}
