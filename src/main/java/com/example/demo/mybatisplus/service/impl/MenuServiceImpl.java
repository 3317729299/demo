package com.example.demo.mybatisplus.service.impl;

import com.example.demo.mybatisplus.model.Menu;
import com.example.demo.mybatisplus.mapper.MenuMapper;
import com.example.demo.mybatisplus.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author trd
 * @since 2022-03-22
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
