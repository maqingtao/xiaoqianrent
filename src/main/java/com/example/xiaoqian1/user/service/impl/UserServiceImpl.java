package com.example.xiaoqian1.user.service.impl;

import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.common.Dictionary.repository.CityDictionaryRepository;
import com.example.xiaoqian1.login.repository.UserLoginRepository;
import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.rent.repository.RoomInformationRepository;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.upload.bean.ImagePath;
import com.example.xiaoqian1.upload.repository.UploadRepository;
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
    public List<RoomInformation> getMyPublish(RoomInformation roomInformation) {
        List<RoomInformation> list = roomInformationRepository.findMyPublishByUserID(
                roomInformation.getUserID());
        return list;
    }

    @Override
    public void delMyPublish(RoomInformation roomInformation) {
        if (roomInformation.getMainID() != null) {
            roomInformationRepository.delRoomByMainID(roomInformation.getMainID());
            roomInformationRepository.delRoomDetailByMainID(roomInformation.getMainID());
            delImage(roomInformation.getMainID());
            uploadRepository.delImagePathByMainID(roomInformation.getMainID());
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
            return ConstantFiled.ERROR;
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
