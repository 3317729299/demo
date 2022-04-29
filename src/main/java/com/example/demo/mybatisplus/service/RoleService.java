package com.example.demo.mybatisplus.service;

import com.example.demo.mybatisplus.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author trd
 * @since 2022-03-15
 */
public interface RoleService extends IService<Role> {


    public List<Role> roleAndMenu();

}
