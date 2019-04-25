package com.example.xiaoqian1.recommend.repository;

import com.example.xiaoqian1.rent.bean.RoomInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.io.Serializable;

public interface RoomRecommendRepository extends JpaRepository<RoomInformation,Serializable> {
}
