package com.example.xiaoqian1.admin;


import com.example.xiaoqian1.admin.bean.ReviewRoomInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewRoomInformation, Serializable> {


    @Query(value = "select * from reviewroom s where s.mainID=?1", nativeQuery = true)
    ReviewRoomInformation getReviewRoomByMainID(String mainID);
    @Query(value = "select * from reviewroom s where s.reviewstatus='正在审核'", nativeQuery = true)
    List<ReviewRoomInformation> findAllReviewRoom();
    @Query(value = "select * from reviewroom s where s.reviewstatus='审核通过'", nativeQuery = true)
    List<ReviewRoomInformation> findAllRoom();
    @Query(value = "select * from reviewroom s where s.userID=?1", nativeQuery = true)
    List<ReviewRoomInformation> findMyPublishByUserID(String userID);

    /*删除审核表信息*/
    @Transactional
    @Modifying
    @Query(value = "delete  from reviewroom  where  reviewroom.mainID=?1", nativeQuery = true)
    void delReviewRoomByMainID(String mainid);
    /*确认审核通过*/
    @Transactional
    @Modifying
    @Query(value = "update  reviewroom set reviewroom.reviewstatus='审核通过' where reviewroom.mainID=?1", nativeQuery = true)
    void confirmReviewRoom(String mainID);

    /*删除用户信息*/
    @Transactional
    @Modifying
    @Query(value = "delete  from person_information   where  person_information.userID=?1", nativeQuery = true)
    void delPersonInformation(String userID);
    /*删除用户账号*/
    @Transactional
    @Modifying
    @Query(value = "delete  from User   where User.userID=?1", nativeQuery = true)
    void delUser(String userID);
}
