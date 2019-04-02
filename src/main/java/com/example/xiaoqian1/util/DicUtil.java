package com.example.xiaoqian1.util;

import com.example.xiaoqian1.common.Dictionary.bean.City;
import com.example.xiaoqian1.common.Dictionary.repository.CityDictionaryRepository;
import com.example.xiaoqian1.common.Dictionary.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: maqingtao
 * @description: 字典工具
 * @create: 2019/4/2
 **/

public class DicUtil  {

    /**
    * @Author: maqingtao
    * @description: 城市数据map
    * @create: 2019/4/2
    **/

    public static Map<String, String> getCityMap(List<City> allCity) {
        Map<String, String> cityMap = new LinkedHashMap<>();
        for (City c : allCity) {
            cityMap.put(c.getCityId(), c.getCityName());
        }
        return cityMap;
    }

}
