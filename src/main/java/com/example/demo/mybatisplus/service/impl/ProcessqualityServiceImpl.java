package com.example.demo.mybatisplus.service.impl;

import com.example.demo.mybatisplus.model.Processquality;
import com.example.demo.mybatisplus.mapper.ProcessqualityMapper;
import com.example.demo.mybatisplus.service.ProcessqualityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 过程质量表 服务实现类
 * </p>
 *
 * @author trd
 * @since 2022-03-22
 */
@Service
public class ProcessqualityServiceImpl extends ServiceImpl<ProcessqualityMapper, Processquality> implements ProcessqualityService {
    @Autowired(required = false)
    private ProcessqualityMapper processqualityMapper;

    @Override
    public Map<String, Object> ListWeek() {
        List<Map<String, Object>> list = processqualityMapper.ListWeek();
        //新建一个map集合来 存储数据
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> stringObjectMap : list) {
            map.put("a" + String.valueOf(stringObjectMap.get("weekday")), String.valueOf(stringObjectMap.get("sum")));
        }
        return map;

    }

    @Override
    public List<Processquality> listToday() {
        return processqualityMapper.listToday();
    }
}
