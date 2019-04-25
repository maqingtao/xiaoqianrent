package com.example.xiaoqian1.user.controller;

import com.alibaba.fastjson.JSON;
import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    /*获取我的收藏部分信息*/
    @RequestMapping(value = "/getmycollect", method = RequestMethod.POST, consumes = "application/json")
    public String getMyCollect(@RequestBody RoomInformation room) {
        List<RoomInformation> result= userService.getMyCollects(room);
        return JSON.toJSONString(result);
    }
    /*发布房源*/
    @RequestMapping(value = "/roompublish", method = RequestMethod.POST, consumes = "application/json")
    public String createRoomPublish(@RequestBody RoomDetail roomDetail) {
        userService.saveRoomInformation(roomDetail);
        return JSON.toJSONString(ConstantFiled.SUCESS_STATUS);
    }

    /**
     * @Author: maqingtao
     * @description: 我的发布
     * @create: 2019/4/5
     **/

    @RequestMapping(value = "/mypublish", method = RequestMethod.POST, consumes = "application/json")
    public String getMyPublish(@RequestBody RoomInformation room) {
        List<RoomInformation> result = userService.getMyPublish(room);
        return JSON.toJSONString(result);
    }

    /**
     * @Author: maqingtao
     * @description: 删除发布信息
     * @create: 2019/4/5
     **/

    @RequestMapping(value = "/delpublish", method = RequestMethod.POST, consumes = "application/json")
    public String delMyPublish(@RequestBody RoomInformation room) {
        userService.delMyPublish(room);
        return JSON.toJSONString(ConstantFiled.SUCCESS);
    }

    @RequestMapping(value = "/getImageName", method = RequestMethod.POST, consumes = "application/json")
    public String getImageName(@RequestBody RoomInformation room) {
        String fileName = userService.getImageName(room.getMainID());
        return JSON.toJSONString(fileName);
    }

    /**
     * @Author: maqingtao
     * @description: detail页面获取图片
     * @create: 2019/4/11
     **/
    @RequestMapping(value = "/getImageNameList", method = RequestMethod.POST, consumes = "application/json")
    public String getImageNameList(@RequestBody RoomInformation room) {
        ArrayList<String> result = userService.getImageNameList(room.getMainID());
        String json = JSON.toJSONString(result);
        return json;
    }
}
