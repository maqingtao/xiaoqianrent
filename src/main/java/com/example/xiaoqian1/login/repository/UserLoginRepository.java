package com.example.xiaoqian1.login.repository;

import com.example.xiaoqian1.login.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface UserLoginRepository extends JpaRepository<User,Serializable> {
    @Query(value = "select * from user s where s.username=?1 and s.password=?2", nativeQuery = true)
    User findUser(String username,String password);
    @Query(value = "select * from user s where s.username=?1", nativeQuery = true)
    User findUser(String username);
    @Query(value = "select * from user s where s.username=?1", nativeQuery = true)
    User findUserID(String username);
}
