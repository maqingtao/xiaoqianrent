package com.example.xiaoqian1.common.Dictionary.controller;

import com.alibaba.fastjson.JSON;
import com.example.xiaoqian1.common.Dictionary.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class CityController {
    @Autowired
    private CityService cityService;
    @RequestMapping(value = "/getAllCity", method = RequestMethod.POST, consumes = "application/json")
    public String getRoomInformation() {
        String json= JSON.toJSONString(cityService.getAllCity());
        return json;
    }
}
