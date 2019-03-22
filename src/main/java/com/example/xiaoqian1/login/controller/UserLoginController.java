package com.example.xiaoqian1.login.controller;

import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
* @Author: maqingtao
* @description: 登录界面验证
* @create: 2019/1/12
**/

@RestController
@RequestMapping(value = "/api")
public class UserLoginController {
    @Autowired
    UserLoginService loginService;
    @RequestMapping(value = "/logins",method = RequestMethod.POST, consumes = "application/json")
    public String getUserlogin(@RequestBody User user,HttpServletRequest request) {
        String loginState=loginService.getLoginState(user);
        if (loginState.equals(ConstantFiled.SUCCESS)){
            HttpSession session=request.getSession();//获取session并将userName存入session对象
            session.setAttribute("userName",user.getUsername());
        }
        return loginState;
    }
}
