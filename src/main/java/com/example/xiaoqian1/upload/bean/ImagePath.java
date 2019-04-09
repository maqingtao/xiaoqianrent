package com.example.xiaoqian1.upload.bean;

import com.example.xiaoqian1.room.bean.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@ToString
@Entity
@Table(name = "imagepath")
public class ImagePath  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    //房间id
    String mainID;
    //图片路径
    String imagePath;

}
