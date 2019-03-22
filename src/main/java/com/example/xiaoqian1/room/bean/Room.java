package com.example.xiaoqian1.room.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
* @Author: maqingtao
* @description: 基本数据类型,所有实体类都要继承
* @create: 2019/3/14
**/

@Setter
@Getter
@ToString
@MappedSuperclass
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    //房间ID
    private String roomDimID;
    //房间类型
    private String roomDimType;
    //地理位置id
    private String spaceDimID;
    //地理位置类型
    private String spaceDimType;
    //房间面积
    private String roomArea;
    //房屋结构
    private String roomStructure;
    //房屋价格
    private  String roomPrice;
    //发布房源信息的时候作为唯一甄别代码,在后面写房源发布时设计如何生成
    private String mainID;
}
