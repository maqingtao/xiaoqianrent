package com.example.xiaoqian1.common.Dictionary.controller;

import com.alibaba.fastjson.JSON;
import com.example.xiaoqian1.common.Dictionary.bean.City;
import com.example.xiaoqian1.common.Dictionary.bean.Province;
import com.example.xiaoqian1.common.Dictionary.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class CityController {
    @Autowired
    private CityService cityService;
    /*根据省份id查出下属城市*/
    @RequestMapping(value = "/getAllCity", method = RequestMethod.POST, consumes = "application/json")
    public String getCity(@RequestBody City city) {
        String json= JSON.toJSONString(cityService.getAllCity(city.getPreID()));
        return json;
    }
    /*查询所有省份*/
    @RequestMapping(value = "/getAllProvince", method = RequestMethod.POST, consumes = "application/json")
    public String getAllProvince(@RequestBody Province province) {
        String json= JSON.toJSONString(cityService.getAllProvince());
        return json;
    }
}
