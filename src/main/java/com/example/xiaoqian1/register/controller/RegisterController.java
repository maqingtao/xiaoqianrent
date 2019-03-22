package com.example.xiaoqian1.register.controller;

import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    RegisterService registerService;
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public String getRegister(@RequestBody User user) {
        String registerSate = registerService.getRegisterState(user);
        return registerSate;
    }
}
