package com.example.xiaoqian1.admin.AdminController;

import com.alibaba.fastjson.JSON;
import com.example.xiaoqian1.admin.AdminService.AdminService;
import com.example.xiaoqian1.admin.bean.Administrator;

import com.example.xiaoqian1.admin.bean.ReviewRoomInformation;
import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.user.bean.PersonInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Author: maqingtao
* @description: 管理员
* @create: 2019/5/20
**/

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping(value = "/logins",method = RequestMethod.POST, consumes = "application/json")
    public String getUserlogin(@RequestBody Administrator administrator, HttpServletRequest request) {
        String loginState=adminService.getLoginState(administrator);
        return loginState;
    }
   /*获取所有未审核的房源*/
    @RequestMapping(value = "/allreview",method = RequestMethod.POST, consumes = "application/json")
    public String getAllReview(@RequestBody ReviewRoomInformation r) {
        List<ReviewRoomInformation> list=adminService.getAllReview(r);
        return JSON.toJSONString(list);
    }
    /*确认审核通过*/
    @RequestMapping(value = "/confirmreview",method = RequestMethod.POST, consumes = "application/json")
    public String setConfirmReview(@RequestBody ReviewRoomInformation r) {
        adminService.setConfirmReview(r);
        return JSON.toJSONString(ConstantFiled.SUCCESS);
    }

    /*获取所有用户发布房源*/
    @RequestMapping(value = "/allroom",method = RequestMethod.POST, consumes = "application/json")
    public String getAllRoom(@RequestBody ReviewRoomInformation r) {
        List<ReviewRoomInformation> result=adminService.getAllRoom();
        return JSON.toJSONString(result);
    }
    /*删除用户房源*/
    @RequestMapping(value = "/delroom",method = RequestMethod.POST, consumes = "application/json")
    public String delRoom(@RequestBody ReviewRoomInformation r) {
       adminService.delRoom(r);
        return JSON.toJSONString(ConstantFiled.SUCCESS);
    }
    /*获取用户信息*/
    @RequestMapping(value = "/getuserinformation",method = RequestMethod.POST, consumes = "application/json")
    public String getAllUserInformation(@RequestBody ReviewRoomInformation r) {
        List<PersonInformation> re=adminService.getAllUserInformation();
        return JSON.toJSONString(re);
    }
    /*删除用户信息*/
    @RequestMapping(value = "/deluser",method = RequestMethod.POST, consumes = "application/json")
    public String delUser(@RequestBody PersonInformation p) {
        adminService.delUser(p);
        return JSON.toJSONString(ConstantFiled.SUCCESS);
    }
}

