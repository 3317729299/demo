package com.example.demo.mybatisPlus.controller;


import cn.hutool.crypto.SecureUtil;
import com.example.demo.mybatisPlus.model.User;
import com.example.demo.mybatisPlus.service.UserService;
import com.example.demo.util.Md5Salt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author trd
 * @since 2022-03-14
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@RequestBody(required=false) User user ) {
        //盐值加密 把盐存入数据库
        String salt = Md5Salt.getRandomString(4);
        //把盐存入数据库
        user.setSalt(salt);
        StringBuffer sb = new StringBuffer(user.getPassword());
        StringBuffer pass = sb.append(salt);
        String spass=new String(pass);
        String md5Str = SecureUtil.md5(spass);

        System.out.println("md5Str"+md5Str);



        user.setPassword(md5Str);
        boolean bool = userService.saveOrUpdate(user);
        if (bool) {
            return "操作成功";
        } else {
            return "失败";
        }
    }





}

