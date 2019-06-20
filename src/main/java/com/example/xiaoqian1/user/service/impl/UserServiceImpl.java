package com.example.xiaoqian1.user.service.impl;

import com.example.xiaoqian1.admin.ReviewRepository;
import com.example.xiaoqian1.admin.bean.ReviewRoomInformation;
import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.common.Dictionary.repository.CityDictionaryRepository;
import com.example.xiaoqian1.login.repository.UserLoginRepository;
import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.rent.repository.RoomInformationRepository;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.upload.bean.ImagePath;
import com.example.xiaoqian1.upload.repository.UploadRepository;
import com.example.xiaoqian1.user.bean.MyCollect;
import com.example.xiaoqian1.user.bean.PersonInformation;
import com.example.xiaoqian1.user.repository.MyCollectRepository;
import com.example.xiaoqian1.user.repository.PersonInformationRepository;
import com.example.xiaoqian1.user.repository.UserRepository;
import com.example.xiaoqian1.user.service.UserService;
import com.example.xiaoqian1.util.DicUtil;
import com.example.xiaoqian1.util.PropertyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CityDictionaryRepository cityDictionaryRepository;
    @Autowired
    RoomInformationRepository roomInformationRepository;
    @Autowired
    UserLoginRepository userLoginRepository;
    @Autowired
    UploadRepository uploadRepository;
    @Autowired
    MyCollectRepository myCollectRepository;
    @Autowired
    PersonInformationRepository personInformationRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public void saveRoomInformation(RoomDetail roomDetail) {
        Map<String, String> city = DicUtil.getCityMap(cityDictionaryRepository.findAll());
        String SpaceDimType = city.get(roomDetail.getSpaceDimID());
        roomDetail.setSpaceDimType(SpaceDimType);
        String roomDimType = PropertyUtil.getRoomDimType(roomDetail.getRoomDimID());
        roomDetail.setRoomDimType(roomDimType);
        //获取用户ID(不是用户名)
        String userID = userLoginRepository.findUserID(roomDetail.getUsername()).getUserID();
        roomDetail.setUserID(userID);
        //获取缩略信息
        setRoom(roomDetail);
        userRepository.save(roomDetail);
    }

    /**
     * @Author: maqingtao
     * @description: 根据userID查找我发布的房源
     * @create: 2019/4/5
     **/
    @Override
    public List<ReviewRoomInformation> getMyPublish(ReviewRoomInformation roomInformation) {
        //查找发布房源需要从审核表中查询
        List<ReviewRoomInformation> list = reviewRepository.findMyPublishByUserID(
                roomInformation.getUserID());
        return list;
    }

    @Override
    public void delMyPublish(RoomInformation roomInformation) {
        if (roomInformation.getMainID() != null) {
            //删除缩略表信息
            roomInformationRepository.delRoomByMainID(roomInformation.getMainID());
            //删除详细表信息
            roomInformationRepository.delRoomDetailByMainID(roomInformation.getMainID());
            delImage(roomInformation.getMainID());
            uploadRepository.delImagePathByMainID(roomInformation.getMainID());
            /*删除审核表内容*/
            reviewRepository.delReviewRoomByMainID(roomInformation.getMainID());
        }
    }

    /*删除房源依赖的图片*/
    public void delImage(String mainID) {
        List<ImagePath> imagePaths = uploadRepository.getImagePathByMainID(mainID);
        for (ImagePath imagePath : imagePaths) {
            String path = ConstantFiled.BASIC_PATH + "\\" + imagePath.getImagePath();
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Override
    public String getImageName(String mainID) {
        List<ImagePath> imagePaths = uploadRepository.getImagePathByMainID(mainID);
        if (imagePaths == null || imagePaths.size() == 0) {
            return ConstantFiled.BASIC_IMAGE_NAME;
        } else {
            return imagePaths.get(0).getImagePath();
        }

    }

    /**
     * @Author: maqingtao
     * @description: detail界面轮播图片名
     * @create: 2019/4/11
     **/

    @Override
    public ArrayList<String> getImageNameList(String mainID) {
        ArrayList<String> nameList = new ArrayList<String>();
        List<ImagePath> imagePaths = uploadRepository.getImagePathByMainID(mainID);
        for (ImagePath imagePath : imagePaths) {
            nameList.add(imagePath.getImagePath());
        }
        while (nameList.size() != 3) {
            nameList.add("default.jpg");
        }
        return nameList;
    }

    /*保存收藏数据*/
    @Override
    public String setCollect(MyCollect myCollect) {
        myCollectRepository.save(myCollect);
        return ConstantFiled.SUCESS_STATUS;
    }

    /*删除收藏数据*/
    @Override
    public void delCollect(MyCollect myCollect) {
        myCollectRepository.delCollectByMainID(myCollect.getUserID(), myCollect.getMainID());
    }

    /*查询收藏状态*/
    @Override
    public String findCollectStatus(MyCollect myCollect) {
        List<MyCollect> myCollects = myCollectRepository.findCollectStatus(myCollect.getUserID(), myCollect.getMainID());
        if (myCollects == null || myCollects.size() == 0) {
            return ConstantFiled.FAILED_STATUS;
        } else {
            return ConstantFiled.SUCESS_STATUS;
        }

    }

    /**
     * @Author: maqingtao
     * @description: 获取我的收藏信息
     * @create: 2019/4/16
     **/

    @Override
    public List<RoomInformation> getMyCollects(RoomInformation roomInformation) {
        List<RoomInformation> result = new ArrayList<>();
        List<MyCollect> lists = myCollectRepository.findMyCollect(roomInformation.getUserID());
        for (MyCollect m : lists) {
            result.add(roomInformationRepository.findRoomByMainID(m.getMainID()));
        }
        return result;
    }

    /**
     * @Author: maqingtao
     * @description: 获取个人信息
     * @create: 2019/5/14
     **/

    @Override
    public PersonInformation getPersonInformation(PersonInformation personInformation) {
        PersonInformation person = personInformationRepository.findPersonInformation(personInformation.getUserID());
        return person;
    }

    /**
     * @Author: maqingtao
     * @description: 修改个人信息
     * @create: 2019/5/14
     **/

    @Override
    public void setPersonInformation(PersonInformation p) {
        personInformationRepository.updatePersonInformation(p.getUserName(), p.getNickName()
                , p.getPhoneNumber(), p.getEMail(), p.getUserID());
        personInformationRepository.updateUser(p.getUserName(), p.getUserID());
    }

    /**
     * @Author: maqingtao
     * @description: 将发布信息放入审核表
     * @create: 2019/5/21
     **/

    @Override
    public void saveReviewRoomInformation(ReviewRoomInformation reviewRoomInformation) {
        Map<String, String> city = DicUtil.getCityMap(cityDictionaryRepository.findAll());
        String SpaceDimType = city.get(reviewRoomInformation.getSpaceDimID());
        reviewRoomInformation.setSpaceDimType(SpaceDimType);
        String roomDimType = PropertyUtil.getRoomDimType(reviewRoomInformation.getRoomDimID());
        reviewRoomInformation.setRoomDimType(roomDimType);
        //获取用户ID(不是用户名)
        String userID = userLoginRepository.findUserID(reviewRoomInformation.getUsername()).getUserID();
        reviewRoomInformation.setUserID(userID);
        //添加审核状态，发布即开始
        reviewRoomInformation.setReviewstatus("正在审核");
        reviewRepository.save(reviewRoomInformation);
    }

    /**
     * @Author: maqingtao
     * @description: 缩略表更新（发布房源后，缩略信息表）
     * @create: 2019/4/3
     **/

    public void setRoom(RoomDetail roomDetail) {
        RoomInformation room = new RoomInformation();
        BeanUtils.copyProperties(roomDetail, room);
        roomInformationRepository.save(room);
    }

}
