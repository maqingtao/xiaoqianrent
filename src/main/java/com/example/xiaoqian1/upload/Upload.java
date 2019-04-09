package com.example.xiaoqian1.upload;

import com.alibaba.fastjson.JSON;
import com.example.xiaoqian1.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
/**
* @Author: maqingtao
* @description: 文件上传
* @create: 2019/4/7
**/

@RestController
@RequestMapping(value = "/api")
public class Upload {
    @Autowired
    UploadService uploadService;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("uploadFile") MultipartFile uploadFile,@RequestParam("mainID")String mainID) throws IOException {
      uploadService.setImage(uploadFile,mainID);
      return JSON.toJSONString("OK");
    }
}
