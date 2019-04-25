package com.example.xiaoqian1.recommend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
/**
* @Author: maqingtao
* @description: 房源与偏好向量的加权相似度
* @create: 2019/4/24
**/

@ToString
@Setter
@Getter
public class UserCollect implements Serializable {
    private String mainID;
    private String price_cos;
    private String area_cos;
    private String type_cos;
    //某房子的加权相似度
    private String similar_item;

}
