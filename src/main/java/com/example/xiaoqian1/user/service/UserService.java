package com.example.xiaoqian1.user.service;

import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
     void saveRoomInformation(RoomDetail roomDetail);
     List<RoomInformation> getMyPublish(RoomInformation roomInformation);
     void delMyPublish(RoomInformation roomInformation);
     String getImageName(String mainID);
     ArrayList<String> getImageNameList(String mainID);
}
