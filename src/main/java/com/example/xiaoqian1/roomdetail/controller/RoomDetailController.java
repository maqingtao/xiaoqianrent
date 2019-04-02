package com.example.xiaoqian1.roomdetail.controller;

import com.alibaba.fastjson.JSON;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.roomdetail.service.RoomDetailService;
import com.example.xiaoqian1.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: maqingtao
 * @description: 详细页detail
 * @create: 2019/3/25
 **/
@RestController
@RequestMapping(value = "/api")
public class RoomDetailController {
    @Autowired
    private RoomDetailService detailService;

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST, consumes = "application/json")
    public String getDetailInformation(@RequestBody RoomDetail roomDetail) {
        List<RoomDetail> list = detailService.getDetail(roomDetail);
        String json = JSON.toJSONString(list,PropertyUtil.getFilter());
        return json;
    }

    @RequestMapping(value = "/getFacility", method = RequestMethod.POST, consumes = "application/json")
    public String getFacility(@RequestBody RoomDetail roomDetail) {
        Map<String,String> map=detailService.getFacility(roomDetail);
        String json = JSON.toJSONString(map);
        return json;
    }
}
