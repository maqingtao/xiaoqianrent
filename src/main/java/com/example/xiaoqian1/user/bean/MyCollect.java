package com.example.xiaoqian1.user.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
* @Author: maqingtao
* @description: 我的收藏
* @create: 2019/4/15
**/
@Setter
@Getter
@ToString
@Entity
@Table(name = "mycollect")
public class MyCollect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String userID;
    private String mainID;
}
