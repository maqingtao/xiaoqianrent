package com.example.xiaoqian1.rent.repository;

import com.example.xiaoqian1.rent.bean.RoomInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface RoomInformationRepository extends JpaRepository<RoomInformation,Serializable>,JpaSpecificationExecutor<RoomInformation> {
}
