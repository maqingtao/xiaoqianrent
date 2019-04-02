package com.example.xiaoqian1.user.repository;

import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<RoomDetail, Serializable> {
}
