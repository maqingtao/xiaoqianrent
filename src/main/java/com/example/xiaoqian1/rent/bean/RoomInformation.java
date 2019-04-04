package com.example.xiaoqian1.rent.bean;

import com.example.xiaoqian1.room.bean.Room;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
* @Author: maqingtao
* @description: 房间缩略信息
* @create: 2019/3/14
**/

@Setter
@Getter
@ToString
@Entity
@Table(name = "room")
public class RoomInformation extends Room implements Serializable {
       @Transient
       private Integer minPrice;
       @Transient
       private Integer maxPrice;
       @Transient
       private Integer minArea;
       @Transient
       private Integer maxArea;

       private String userID;
}
