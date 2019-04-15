package com.example.xiaoqian1.common.Dictionary.repository;

import com.example.xiaoqian1.common.Dictionary.bean.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ProvinceDictionaryRepository extends JpaRepository<Province,Serializable> {
}
