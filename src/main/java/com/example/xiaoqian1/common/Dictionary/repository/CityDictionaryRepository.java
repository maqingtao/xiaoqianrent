package com.example.xiaoqian1.common.Dictionary.repository;

import com.example.xiaoqian1.common.Dictionary.bean.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CityDictionaryRepository extends JpaRepository<City,Serializable> {
}
