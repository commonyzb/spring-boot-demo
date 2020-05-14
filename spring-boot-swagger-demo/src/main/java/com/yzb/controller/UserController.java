package com.yzb.controller;

import com.yzb.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过用户id获取用户信息", response = User.class, httpMethod = "GET")
    public User getUser(@PathVariable int id) {
        if (id == 1) {
            return new User();
        } else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User getUser(@RequestBody User user) {
        return user;
    }

}
