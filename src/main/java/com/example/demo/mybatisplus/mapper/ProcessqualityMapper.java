package com.example.demo.mybatisplus.mapper;

import com.example.demo.mybatisplus.model.Processquality;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 过程质量表 Mapper 接口
 * </p>
 *
 * @author trd
 * @since 2022-03-22
 */
public interface ProcessqualityMapper extends BaseMapper<Processquality> {
    public List<Processquality> listToday();

    public List<Map<String, Object>> ListWeek();


}
