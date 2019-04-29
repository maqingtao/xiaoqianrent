package com.example.xiaoqian1.roomdetail.repository;

import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.io.Serializable;


public interface RoomDetailRepository extends JpaRepository<RoomDetail, Serializable>, JpaSpecificationExecutor<RoomDetail> {
    @Query(value = "select * from roomdetail  where roomdetail.mainID=?1", nativeQuery = true)
    RoomDetail getRoomDetailByMainID(String mainid);
}
