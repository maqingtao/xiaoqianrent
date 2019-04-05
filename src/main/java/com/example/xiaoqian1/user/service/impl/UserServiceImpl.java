package com.example.xiaoqian1.user.service.impl;

import com.example.xiaoqian1.common.Dictionary.repository.CityDictionaryRepository;
import com.example.xiaoqian1.login.repository.UserLoginRepository;
import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.rent.repository.RoomInformationRepository;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.user.repository.UserRepository;
import com.example.xiaoqian1.user.service.UserService;
import com.example.xiaoqian1.util.DicUtil;
import com.example.xiaoqian1.util.PropertyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @Override
    public void saveRoomInformation(RoomDetail roomDetail) {
        Map<String, String> city = DicUtil.getCityMap(cityDictionaryRepository.findAll());
        String SpaceDimType = city.get(roomDetail.getSpaceDimID());
        roomDetail.setSpaceDimType(SpaceDimType);
        String roomDimType = PropertyUtil.getRoomDimType(roomDetail.getRoomDimID());
        roomDetail.setRoomDimType(roomDimType);
        //使用时间戳作为唯一识别mainID
        String timers=String.valueOf(System.currentTimeMillis());
        String mainID=timers.substring(0,timers.length()-3);
        roomDetail.setMainID(mainID);
        //获取用户ID(不是用户名)
        String userID=userLoginRepository.findUserID(roomDetail.getUsername()).getUserID();
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
        List<RoomInformation> list=roomInformationRepository.findMyPublishByUserID(
                roomInformation.getUserID());
        return list;
    }

    @Override
    public void delMyPublish(RoomInformation roomInformation) {
        roomInformationRepository.delRoomByMainID(roomInformation.getMainID());
        roomInformationRepository.delRoomDetailByMainID(roomInformation.getMainID());
    }

    /**
    * @Author: maqingtao
    * @description: 缩略表更新（发布房源后，缩略信息表）
    * @create: 2019/4/3
    **/

    public void setRoom(RoomDetail roomDetail)
    {
        RoomInformation room=new RoomInformation();
        BeanUtils.copyProperties(roomDetail,room);
        roomInformationRepository.save(room);
    }


}
