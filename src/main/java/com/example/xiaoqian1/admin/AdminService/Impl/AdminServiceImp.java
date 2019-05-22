package com.example.xiaoqian1.admin.AdminService.Impl;

import com.example.xiaoqian1.admin.AdminRepository;
import com.example.xiaoqian1.admin.AdminService.AdminService;
import com.example.xiaoqian1.admin.ReviewRepository;
import com.example.xiaoqian1.admin.bean.Administrator;
import com.example.xiaoqian1.admin.bean.ReviewRoomInformation;
import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.rent.repository.RoomInformationRepository;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.roomdetail.repository.RoomDetailRepository;
import com.example.xiaoqian1.upload.repository.UploadRepository;
import com.example.xiaoqian1.user.bean.PersonInformation;
import com.example.xiaoqian1.user.repository.PersonInformationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    RoomDetailRepository roomDetailRepository;
    @Autowired
    RoomInformationRepository roomInformationRepository;
    @Autowired
    UploadRepository uploadRepository;
    @Autowired
    PersonInformationRepository personInformationRepository;
    /*获取登录状态*/
    @Override
    public String getLoginState(Administrator administrator) {
        String username = administrator.getUsername();
        String password = administrator.getPassword();
        Administrator admin = adminRepository.findAdministrator(username, password);
        if (admin == null) {
            return ConstantFiled.ERROR;
        } else {
            return ConstantFiled.SUCCESS;
        }
    }

    /*获取正在审核房源*/
    @Override
    public List<ReviewRoomInformation> getAllReview(ReviewRoomInformation reviewRoomInformation) {
        List<ReviewRoomInformation> result = reviewRepository.findAllReviewRoom();
        return result;
    }

    /*确认审核通过*/
    @Override
    public void setConfirmReview(ReviewRoomInformation reviewRoomInformation) {
        if (reviewRoomInformation.getMainID() != null) {
            //1.将审核状态改为审核通过
            reviewRepository.confirmReviewRoom(reviewRoomInformation.getMainID());
            //2.将审核表的房源信息传给detail表以及room表
            ReviewRoomInformation review = reviewRepository.getReviewRoomByMainID(reviewRoomInformation.getMainID());
            RoomDetail roomDetail = new RoomDetail();
            BeanUtils.copyProperties(review, roomDetail);
            //2.1保存到detail
            roomDetailRepository.save(roomDetail);
            RoomInformation room = new RoomInformation();
            BeanUtils.copyProperties(roomDetail, room);
            //2.2保存到room里
            roomInformationRepository.save(room);
        }

    }

    /*获取审核通过房源*/
    @Override
    public List<ReviewRoomInformation> getAllRoom() {
        List<ReviewRoomInformation> result = reviewRepository.findAllRoom();
        return result;
    }

    /*管理员删除房源*/
    @Override
    public void delRoom(ReviewRoomInformation reviewRoomInformation) {
        if (reviewRoomInformation.getMainID() != null) {
            roomInformationRepository.delRoomByMainID(reviewRoomInformation.getMainID());
            roomInformationRepository.delRoomDetailByMainID(reviewRoomInformation.getMainID());
            reviewRepository.delReviewRoomByMainID(reviewRoomInformation.getMainID());
            uploadRepository.delImagePathByMainID(reviewRoomInformation.getMainID());
        }

    }
    /*获取全部用户信息*/
    @Override
    public List<PersonInformation> getAllUserInformation() {
        List<PersonInformation> result=personInformationRepository.findAll();
        return result;
    }
    /*删除用户信息*/
    @Override
    public void delUser(PersonInformation p) {
        //1.删除账号信息
        reviewRepository.delPersonInformation(p.getUserID());
        //2,删除登录账号
        reviewRepository.delUser(p.getUserID());
    }
}
