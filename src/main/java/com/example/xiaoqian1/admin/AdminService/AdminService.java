package com.example.xiaoqian1.admin.AdminService;

import com.example.xiaoqian1.admin.bean.Administrator;
import com.example.xiaoqian1.admin.bean.ReviewRoomInformation;
import com.example.xiaoqian1.user.bean.PersonInformation;

import java.util.List;

public interface AdminService {
     String getLoginState(Administrator administrator);
     List<ReviewRoomInformation> getAllReview(ReviewRoomInformation reviewRoomInformation);
     void setConfirmReview(ReviewRoomInformation reviewRoomInformation);
     List<ReviewRoomInformation> getAllRoom();
     void delRoom(ReviewRoomInformation reviewRoomInformation);
     List<PersonInformation> getAllUserInformation();
     void delUser(PersonInformation p);
}
