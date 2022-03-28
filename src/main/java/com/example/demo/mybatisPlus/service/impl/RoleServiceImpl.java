package com.example.demo.mybatisPlus.service.impl;

import com.example.demo.mybatisPlus.model.Role;
import com.example.demo.mybatisPlus.mapper.RoleMapper;
import com.example.demo.mybatisPlus.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author trd
 * @since 2022-03-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> roleAndMenu() {
        return roleMapper.roleAndMenu();
    }
}
