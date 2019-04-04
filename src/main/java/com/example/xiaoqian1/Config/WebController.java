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
    @RequestMapping("/personal")
    public String personalHtml() {
        return "personal";
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
        return "detail";
    }
    //测试
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/index")
    public String idexHtml() {
        return "/index";
    }
    //用户中心--房源发布页面
    @RequestMapping("/room-publish")
    public String roomPublish() {
        return "/person/room-publish";
    }

    //用户中心--房源发布页面
    @RequestMapping("/my-publish")
    public String myPublish() {
        return "/person/my-publish";
    }
}
