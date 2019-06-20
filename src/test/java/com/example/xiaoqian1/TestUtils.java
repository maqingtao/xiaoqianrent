package com.example.xiaoqian1;

import com.example.xiaoqian1.admin.bean.ReviewRoomInformation;
import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.rent.bean.RoomInformation;

public class TestUtils {
    public static User getUser() {
        User user = null;
        user = new User();
        user.setUsername("MrThree");
        user.setPassword("123456");
        user.setUserID("14");
        return user;
    }
    public static RoomInformation getRoomInformation() {
        RoomInformation roomInformation=new RoomInformation();
        roomInformation.setMinArea(10);
        roomInformation.setMaxArea(100);
        roomInformation.setMinPrice(1000);
        roomInformation.setMaxPrice(1500);
        roomInformation.setSpaceDimID("230001");
        roomInformation.setRoomDimID("10");
        return roomInformation;
    }

    public static ReviewRoomInformation getReviewRoom()
    {
        ReviewRoomInformation r=new ReviewRoomInformation();
        r.setUserID("14");
        return r;
    }
}
