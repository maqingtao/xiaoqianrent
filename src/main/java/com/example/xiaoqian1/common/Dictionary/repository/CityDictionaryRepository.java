package com.example.xiaoqian1.common.Dictionary.repository;

import com.example.xiaoqian1.common.Dictionary.bean.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface CityDictionaryRepository extends JpaRepository<City,Serializable> {
    @Query(value = "select * from city_dic c where c.preID=?1", nativeQuery = true)
    List<City> findCityByPreID(String provinceID);
}
