package com.example.xiaoqian1.login.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
@Setter
@Getter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String userID;
    @Transient
    private String mainID;

}
