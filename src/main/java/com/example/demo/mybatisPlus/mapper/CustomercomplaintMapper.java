package com.example.demo.mybatisPlus.mapper;

import com.example.demo.mybatisPlus.model.Customercomplaint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户抱怨表 Mapper 接口
 * </p>
 *
 * @author trd
 * @since 2022-03-22
 */
public interface CustomercomplaintMapper extends BaseMapper<Customercomplaint> {

    public List<Customercomplaint> listToday ();

    List<Map<String, Object>> ListWeek();

}
