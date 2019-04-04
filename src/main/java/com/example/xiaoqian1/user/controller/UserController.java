package com.example.xiaoqian1.user.controller;

import com.alibaba.fastjson.JSON;
import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: maqingtao
 * @description: 用户中心
 * @create: 2019/4/2
 **/

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/roompublish", method = RequestMethod.POST, consumes = "application/json")
    public String createRoomPublish(@RequestBody RoomDetail roomDetail) {
        userService.saveRoomInformation(roomDetail);
        return JSON.toJSONString(ConstantFiled.SUCESS_STATUS);
    }
}
