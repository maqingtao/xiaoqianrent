package com.example.xiaoqian1.common.Dictionary.service;

import com.example.xiaoqian1.common.Dictionary.bean.City;
import com.example.xiaoqian1.common.Dictionary.bean.Province;

import java.util.List;

public interface CityService {
    List<City> getAllCity(String provinceID);
    List<Province> getAllProvince();
}
