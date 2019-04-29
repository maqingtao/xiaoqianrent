package com.example.xiaoqian1.recommend.Service;

import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import java.util.List;


public interface RoomRecommendService {
    List<RoomDetail> getRecommend(User user);
}
