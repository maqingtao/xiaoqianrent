package com.example.xiaoqian1.user.repository;

import com.example.xiaoqian1.user.bean.MyCollect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface MyCollectRepository extends JpaRepository<MyCollect, Serializable> {
    //查询收藏状态
    @Query(value = "select * from mycollect m where m.userID=?1 and m.mainID=?2", nativeQuery = true)
    List<MyCollect> findCollectStatus(String userID, String mainID);

    //取消收藏
    @Transactional
    @Modifying
    @Query(value = "delete  from  mycollect  where mycollect.userID=?1 and mycollect.mainID=?2", nativeQuery = true)
    void delCollectByMainID(String userID, String mainID);
}
