package com.example.xiaoqian1.rent.controller;

import com.alibaba.fastjson.JSON;
import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.rent.service.RoomInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: maqingtao
 * @description: 缩略信息展示
 * @create: 2019/3/14
 **/


@RestController
@RequestMapping(value = "/api")
public class RoomController {
    @Autowired
    RoomInformationService roomInformationService;

    @RequestMapping(value = "/getPageMessage/{page}", method = RequestMethod.POST, consumes = "application/json")
    public String getPageMessage(@PathVariable("page") int page, @RequestBody RoomInformation information) {
        //设定
        PageRequest pageRequest = PageRequest.of(page, 8);
        Page<RoomInformation> informationPage = roomInformationService.getPageInformation(pageRequest);
        List<RoomInformation> list = informationPage.getContent();
        String json = JSON.toJSONString(list);
        return json;
    }

    /**
     * @Author: maqingtao
     * @description: 获取总页数
     * @create: 2019/3/21
     **/

    @RequestMapping(value = "/getPageSize", method = RequestMethod.POST, consumes = "application/json")
    public String getPageNumber(@RequestBody RoomInformation information) {
        PageRequest pageRequest = PageRequest.of(0, 8);
        Page<RoomInformation> informationPage = roomInformationService.getSelectInformation(pageRequest, information);
        int number = informationPage.getTotalPages();
        String json = JSON.toJSONString(number);
        return json;
    }

    @RequestMapping(value = "/getSelectMessage/{page}", method = RequestMethod.POST, consumes = "application/json")
    public String getSelectMessage(@PathVariable("page") int page, @RequestBody RoomInformation information) {
        String json;
        //设定
        PageRequest pageRequest = PageRequest.of(page, 8);
        Page<RoomInformation> informationPage = roomInformationService.getSelectInformation(pageRequest, information);
        List<RoomInformation> list = informationPage.getContent();
        if (list == null || list.size() == 0) {
            json = JSON.toJSONString(ConstantFiled.FAILED_STATUS);
        } else {
            json = JSON.toJSONString(list);
        }
        return json;
    }
}
