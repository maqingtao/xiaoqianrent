package com.example.xiaoqian1.user.repository;

import com.example.xiaoqian1.user.bean.PersonInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;


public interface PersonInformationRepository extends JpaRepository<PersonInformation, Serializable> {
    //查询个人信息
    @Query(value = "select * from person_information p where p.userID=?1", nativeQuery = true)
    PersonInformation findPersonInformation(String userID);
    //修改个人信息
    @Transactional
    @Modifying
    @Query(value = "update person_information  set person_information.user_name=?1,person_information.nick_name=?2,person_information.phone_number=?3,person_information.e_mail=?4 where person_information.userID=?5", nativeQuery = true)
    void updatePersonInformation(String userName,String nickName,String phoneNumber,String email,String userID);
}
