package com.example.xiaoqian1.rent.repository;

import com.example.xiaoqian1.rent.bean.RoomInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface RoomInformationRepository extends JpaRepository<RoomInformation, Serializable>, JpaSpecificationExecutor<RoomInformation> {
    /**
     * @Author: maqingtao
     * @description: 寻找我的发布
     * @create: 2019/4/5
     **/

    @Query(value = "select * from room s where s.userID=?1", nativeQuery = true)
    List<RoomInformation> findMyPublishByUserID(String userID);

    @Query(value = "select * from room s where s.mainID=?1", nativeQuery = true)
    RoomInformation findRoomByMainID(String mainID);

    /**
     * @Author: maqingtao
     * @description: 删除
     * @create: 2019/4/5
     **/
    @Transactional
    @Modifying
    @Query(value = "delete  from room  where room.mainID=?1", nativeQuery = true)
    void delRoomByMainID(String mainid);
    @Transactional
    @Modifying
    @Query(value = "delete  from roomdetail  where roomdetail.mainID=?1", nativeQuery = true)
    void delRoomDetailByMainID(String mainid);
}
