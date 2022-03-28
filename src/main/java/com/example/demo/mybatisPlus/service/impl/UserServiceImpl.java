package com.example.demo.mybatisPlus.service.impl;

import com.example.demo.mybatisPlus.model.User;
import com.example.demo.mybatisPlus.mapper.UserMapper;
import com.example.demo.mybatisPlus.service.UserService;
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
