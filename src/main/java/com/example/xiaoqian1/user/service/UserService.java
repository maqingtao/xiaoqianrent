package com.example.xiaoqian1.user.service;

import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;

import java.util.List;

public interface UserService {
     void saveRoomInformation(RoomDetail roomDetail);
     List<RoomInformation> getMyPublish(RoomInformation roomInformation);
     void delMyPublish(RoomInformation roomInformation);
}
