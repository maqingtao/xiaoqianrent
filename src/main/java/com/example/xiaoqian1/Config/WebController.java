package com.example.xiaoqian1.Config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Author: maqingtao
* @description: 静态页面
* @create: 2019/3/15
**/

@Controller
public class WebController {
    //注册
    @RequestMapping("/createAccount")
    public String register() {
        return "register";
    }
    //index
    @RequestMapping("/index")
    public String indexHtml() {
        return "index";
    }
    //登录页面
    @RequestMapping("/")
    public String loginHtml() {
        return "/login";
    }
    //租房页面
    @RequestMapping("/rentroom")
    public String rentroom() {
        return "rentroom";
    }
    //租房页面
    @RequestMapping("/detail")
    public String detail() {
        return "single";
    }
    //测试
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}
