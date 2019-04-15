package com.example.xiaoqian1.common.Dictionary.service.impl;

import com.example.xiaoqian1.common.Dictionary.bean.City;
import com.example.xiaoqian1.common.Dictionary.bean.Province;
import com.example.xiaoqian1.common.Dictionary.repository.CityDictionaryRepository;
import com.example.xiaoqian1.common.Dictionary.repository.ProvinceDictionaryRepository;
import com.example.xiaoqian1.common.Dictionary.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
* @Author: maqingtao
* @description: 获取城市基本数据信息
* @create: 2019/3/20
**/

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDictionaryRepository cityDictionaryRepository;
    @Autowired
    private ProvinceDictionaryRepository provinceDictionaryRepository;
    @Override
    public List<City> getAllCity(String provinceID) {
        List<City> allCity=cityDictionaryRepository.findCityByPreID(provinceID);
        return allCity;
    }

    @Override
    public List<Province> getAllProvince() {
        List<Province> allProvince=provinceDictionaryRepository.findAll();
        return allProvince;
    }
}
