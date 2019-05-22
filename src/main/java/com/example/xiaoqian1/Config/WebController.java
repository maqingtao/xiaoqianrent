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
        return "index";
    }
    //用户中心--房源发布页面
    @RequestMapping("/room-publish")
    public String roomPublish() {
        return "/person/room-publish";
    }

    //用户中心--我的发布
    @RequestMapping("/my-publish")
    public String myPublish() {
        return "/person/my-publish";
    }

    //用户中心--房源收藏
    @RequestMapping("/my-collect")
    public String myCollect() {
        return "/person/my-collect";
    }

    //用户中心--房源收藏
    @RequestMapping("/my-information")
    public String myInformation() {
        return "/person/my-information";
    }

    //推荐城市搜索
    @RequestMapping("/cityrent")
    public String cityrent() {
        return "/cityrent";
    }

    //管理员
    @RequestMapping("/admin")
    public String admin() {
        return "/admin/index";
    }
    //全部房源
    @RequestMapping("/adminAllroom")
    public String adminAllroom() {
        return "/admin/allroom";
    }
    //待审核
    @RequestMapping("/adminReview")
    public String adminReview() {
        return "/admin/review";
    }
    //用户信息
    @RequestMapping("/adminUserInformation")
    public String adminUserInformation() {
        return "/admin/userInformation";
    }
    //用户信息
    @RequestMapping("/adminLogin")
    public String adminLogin() {
        return "/admin/sign-in";
    }
}
