package com.yzb.common;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public CommonResponse authorizationExceptionHandler(HttpServletResponse response){
        response.setStatus(401);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(401);
        commonResponse.setMessage("无权限访问！");
        return commonResponse;
    }

}
