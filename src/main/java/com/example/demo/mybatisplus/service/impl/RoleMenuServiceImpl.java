package com.example.demo.mybatisplus.service.impl;

import com.example.demo.mybatisplus.model.RoleMenu;
import com.example.demo.mybatisplus.mapper.RoleMenuMapper;
import com.example.demo.mybatisplus.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author trd
 * @since 2022-03-15
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
