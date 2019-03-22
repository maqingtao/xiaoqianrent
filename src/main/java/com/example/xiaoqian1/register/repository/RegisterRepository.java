package com.example.xiaoqian1.register.repository;

import com.example.xiaoqian1.login.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RegisterRepository extends JpaRepository<User, Long> {

    @Query(value = "insert into user(username,password) values('1','2')",nativeQuery = true)
     User setRegister(String name, String pwd);
}
