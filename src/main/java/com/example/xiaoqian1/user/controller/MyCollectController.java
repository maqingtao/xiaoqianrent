package com.example.xiaoqian1.user.controller;

import com.alibaba.fastjson.JSON;
import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.upload.service.UploadService;
import com.example.xiaoqian1.user.bean.MyCollect;
import com.example.xiaoqian1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class MyCollectController {
    @Autowired
    UserService userService;

    /*收藏房源*/
    @RequestMapping(value = "/mycollect", method = RequestMethod.POST, consumes = "application/json")
    public String myCollect(@RequestBody MyCollect myCollect) {
        String status = userService.setCollect(myCollect);
        return JSON.toJSONString(ConstantFiled.SUCESS_STATUS);
    }

    /*取消收藏*/
    @RequestMapping(value = "/cancelcollect", method = RequestMethod.POST, consumes = "application/json")
    public String cancelCollect(@RequestBody MyCollect myCollect) {
        userService.delCollect(myCollect);
        return JSON.toJSONString(ConstantFiled.SUCESS_STATUS);
    }

    /*收藏状态*/
    @RequestMapping(value = "/collectstatus", method = RequestMethod.POST, consumes = "application/json")
    public String collectStatus(@RequestBody MyCollect myCollect) {
        String status = userService.findCollectStatus(myCollect);
        return JSON.toJSONString(status);
    }
}
