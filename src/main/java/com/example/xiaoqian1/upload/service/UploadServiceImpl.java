package com.example.xiaoqian1.upload.service;

import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.upload.bean.ImagePath;
import com.example.xiaoqian1.upload.repository.UploadRepository;
import com.example.xiaoqian1.user.bean.PersonInformation;
import com.example.xiaoqian1.user.repository.PersonInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: maqingtao
 * @description: 图片上传部分
 * @create: 2019/4/8
 **/
@Service
public class UploadServiceImpl implements UploadService {

    /**
    * @Author: maqingtao
    * @description: 实现前端上传图片复制到本地，并且在数据库中保存地址
    * @create: 2019/4/8
    **/
    @Autowired
    private UploadRepository uploadRepository;
    @Autowired
    PersonInformationRepository personInformationRepository;
    @Override
    public void setImage(MultipartFile uploadFile,String mainID){
        String timers = String.valueOf(System.currentTimeMillis());
        String uid = timers;
        //将uid加入文件名防止文件名重复
        String fileName = uploadFile.getOriginalFilename().toLowerCase();
        //拼接文件地址
        String filePath = ConstantFiled.BASIC_PATH + "\\" + uid + fileName;
        try {
            uploadFile.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //保存存储路径到数据库
        ImagePath imagePath=new ImagePath();
        imagePath.setMainID(mainID);
        imagePath.setImagePath(uid+fileName);
        uploadRepository.save(imagePath);
    }
    /**
    * @Author: maqingtao
    * @description: 上传头像
    * @create: 2019/5/14
    **/

    @Override
    public void upLoadUserFace(MultipartFile uploadFile, String userID) {
        String timers = String.valueOf(System.currentTimeMillis());
        String uid = timers;
        //将uid加入文件名防止文件名重复
        String fileName = uploadFile.getOriginalFilename().toLowerCase();
        //拼接文件地址
        String filePath = ConstantFiled.BASIC_PATH + "\\" + uid + fileName;
        try {
            uploadFile.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PersonInformation p=personInformationRepository.findPersonInformation(userID);
        String path = ConstantFiled.BASIC_PATH + "\\" + p.getIconPath();
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        uploadRepository.updateUserFace(uid+fileName,userID);
    }
}
