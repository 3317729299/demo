package com.example.demo.mybatisPlus.mapper;

import com.example.demo.mybatisPlus.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author trd
 * @since 2022-03-15
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

     /**
      * 查询角色和菜单
      * @return
      */
     List<Role> roleAndMenu ();

}
