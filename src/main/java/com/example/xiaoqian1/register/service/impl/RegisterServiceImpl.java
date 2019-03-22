package com.example.xiaoqian1.register.service.impl;

import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.login.repository.UserLoginRepository;
import com.example.xiaoqian1.register.repository.RegisterRepository;
import com.example.xiaoqian1.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
* @Author: maqingtao
* @description: 注册
* @create: 2019/1/13
**/

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserLoginRepository userLoginRepository;
    @Autowired
    RegisterRepository registerRepository;

    @Override
    public String getRegisterState(User user) {
        /*判断一下数据库中是否有重复的用户名，避免重复*/
        if (userLoginRepository.findUser(user.getUsername(), user.getPassword()) == null) {
registerRepository.save(user);
//            registerRepository.setRegister(user.getUsername(), user.getPassword());
            return ConstantFiled.SUCCESS;
        }
        return ConstantFiled.ERROR;
    }
}
