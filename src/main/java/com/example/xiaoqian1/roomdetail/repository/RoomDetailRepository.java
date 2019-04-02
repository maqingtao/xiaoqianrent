package com.example.xiaoqian1.roomdetail.repository;

import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface RoomDetailRepository extends JpaRepository<RoomDetail, Serializable>, JpaSpecificationExecutor<RoomDetail> {
}
