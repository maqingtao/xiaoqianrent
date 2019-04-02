package com.example.xiaoqian1.roomdetail.service;

import com.example.xiaoqian1.roomdetail.bean.RoomDetail;

import java.util.List;
import java.util.Map;

public interface RoomDetailService {
    public List<RoomDetail> getDetail(RoomDetail room);
    public Map<String,String> getFacility(RoomDetail room);

}
