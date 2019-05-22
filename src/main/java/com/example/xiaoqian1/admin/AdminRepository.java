package com.example.xiaoqian1.admin;

import com.example.xiaoqian1.admin.bean.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface AdminRepository extends JpaRepository<Administrator,Serializable> {
    @Query(value = "select * from administrator s where s.username=?1 and s.password=?2", nativeQuery = true)
    Administrator findAdministrator(String username, String password);
}
