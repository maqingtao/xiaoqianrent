package com.example.xiaoqian1.login.service.impl;

import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.login.repository.UserLoginRepository;
import com.example.xiaoqian1.login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: maqingtao
 * @description: 处理登录逻辑
 * @create: 2019/1/12
 **/

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginRepository loginRepository;

    @Override
    public String getLoginState(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User state = loginRepository.findUser(username, password);
        if (state == null) {
            return ConstantFiled.ERROR;
        } else {
            return ConstantFiled.SUCCESS;
        }
    }
}
