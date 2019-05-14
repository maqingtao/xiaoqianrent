package com.example.xiaoqian1.upload.repository;


import com.example.xiaoqian1.upload.bean.ImagePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface UploadRepository extends JpaRepository<ImagePath,Serializable> {
    @Transactional
    @Modifying
    @Query(value = "delete  from imagepath  where imagepath.mainID=?1", nativeQuery = true)
    void delImagePathByMainID(String mainid);

    @Query(value = "select * from imagepath  where imagepath.mainID=?1", nativeQuery = true)
    List<ImagePath> getImagePathByMainID(String mainid);
    //修改头像
    @Transactional
    @Modifying
    @Query(value = "update person_information  set person_information.icon_path=?1 where person_information.userID=?2", nativeQuery = true)
    void updateUserFace(String iconPath,String userID);

}
