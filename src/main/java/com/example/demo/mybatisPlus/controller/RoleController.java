package com.example.demo.mybatisPlus.controller;


import com.example.demo.mybatisPlus.annotation.DS;
import com.example.demo.mybatisPlus.model.Role;
import com.example.demo.mybatisPlus.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author trd
 * @since 2022-03-15
 */
@RestController

@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @DS("R")
    @GetMapping("/list")
    public List<Role> roleAndMenu() {

        return roleService.roleAndMenu();
    }

}

