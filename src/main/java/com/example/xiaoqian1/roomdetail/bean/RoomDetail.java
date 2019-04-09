package com.example.xiaoqian1.roomdetail.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.xiaoqian1.room.bean.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Setter
@Getter
@ToString
@Entity
@Table(name = "roomdetail")
public class RoomDetail extends Room implements Serializable {
    //发布房源用户id
    @JSONField(ordinal=1)
    private String userID;
    //房间朝向
    @JSONField(ordinal=2)
    private String roomOriented;
    //房屋楼层
    @JSONField(ordinal=3)
    private String roomFloor;
    //电梯
    @JSONField(ordinal=4)
    private String elevator;
    //签约日期
    @JSONField(ordinal=5)
    private String check_in_date;
    //可签时长
    @JSONField(ordinal=6)
    private String signing_time;
    //房屋状态
    @JSONField(ordinal=7)
    private String house_status;
    //条件
    @JSONField(ordinal=8)
    private String facility;
    //描述
    @JSONField(ordinal=9)
    private String describes;
    @Transient
    private String username;
}
