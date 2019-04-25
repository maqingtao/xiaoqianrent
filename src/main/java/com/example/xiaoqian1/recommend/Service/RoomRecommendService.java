package com.example.xiaoqian1.recommend.Service;

import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.recommend.UserCollect;
import java.util.Set;

public interface RoomRecommendService {
    Set<UserCollect> getRecommend(User user);
}
