package com.example.xiaoqian1.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    void setImage(MultipartFile uploadFile,String mainID);
    void upLoadUserFace(MultipartFile uploadFile,String userID);
}
