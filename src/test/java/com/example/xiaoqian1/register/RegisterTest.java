package com.example.xiaoqian1.register;

import com.example.xiaoqian1.TestUtils;
import com.example.xiaoqian1.Xiaoqian1Application;
import com.example.xiaoqian1.common.ConstantFiled;
import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.register.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Xiaoqian1Application.class)
public class RegisterTest {
    @Autowired
    RegisterService registerService;

    //测试用户名存在注册情况，返回错误信息
    @Test
    public void registerEexsitTest() {
        String state = registerService.getRegisterState(TestUtils.getUser());
        assertTrue(state.equals(ConstantFiled.ERROR));
    }

    //测试用户名注，返回成功信息
    @Test
    public void registerNotEexsitTest() {
        User user=new User();
        user.setUsername("T"+String.valueOf(System.currentTimeMillis()));
        user.setPassword(String.valueOf(System.currentTimeMillis()));
        String state = registerService.getRegisterState(user);
        assertTrue(state.equals(ConstantFiled.SUCCESS));
    }

}
