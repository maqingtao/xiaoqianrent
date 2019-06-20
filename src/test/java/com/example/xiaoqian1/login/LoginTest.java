package com.example.xiaoqian1.login;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.xiaoqian1.Xiaoqian1Application;
import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.login.service.UserLoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Author: maqingtao
 * @description: 登录模块测试
 * @create: 2019/5/31
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Xiaoqian1Application.class)
public class LoginTest {
    @Autowired
    UserLoginService loginService;
    public User user = null;
    public User nouser = null;
    @Before
    public void setUser() {
        user = new User();
        user.setUsername("MrThree");
        user.setPassword("123456");
        user.setUserID("14");
    }
    @Before
    public void setNoUser() {
        nouser = new User();
        nouser.setUsername("11");
        nouser.setPassword("123456");
        nouser.setUserID("14");
    }

    //测试成功登陆状态
    @Test
    public void testUserLoginSucess() {
        assertTrue(loginService.getLoginState(user).equals(ConstantFiled.SUCCESS));
    }
    //测试错误登陆状态
    @Test
    public void testUserLoginFaild() {
        assertTrue(loginService.getLoginState(nouser).equals(ConstantFiled.ERROR));
    }
    //测试查询用户ID
    @Test
    public void testGetUserID() {
        assertNotNull(loginService.getUserID(user).equals("14"));
    }
}




