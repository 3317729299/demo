package com.example.demo.mybatisplus.service.impl;

import com.example.demo.mybatisplus.model.User;
import com.example.demo.mybatisplus.mapper.UserMapper;
import com.example.demo.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author trd
 * @since 2022-03-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
