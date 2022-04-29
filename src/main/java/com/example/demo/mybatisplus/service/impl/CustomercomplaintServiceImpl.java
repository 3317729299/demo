package com.example.demo.mybatisplus.service.impl;

import com.example.demo.mybatisplus.model.Customercomplaint;
import com.example.demo.mybatisplus.mapper.CustomercomplaintMapper;
import com.example.demo.mybatisplus.service.CustomercomplaintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户抱怨表 服务实现类
 * </p>
 *
 * @author trd
 * @since 2022-03-22
 */
@Service
public class CustomercomplaintServiceImpl extends ServiceImpl<CustomercomplaintMapper, Customercomplaint> implements CustomercomplaintService {

    @Autowired
    public CustomercomplaintMapper customercomplaintMapper;


    @Override
    public List<Customercomplaint> listToday() {
        return customercomplaintMapper.listToday();
    }

    @Override
    public List<Map<String, Object>> ListWeek() {
        return customercomplaintMapper.ListWeek();
    }

    @Override
    public Map<String, Object> week() {
        List<Map<String, Object>> list = customercomplaintMapper.ListWeek();
        //新建一个map集合来 存储数据
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> stringObjectMap : list) {
            map.put("a" + String.valueOf(stringObjectMap.get("weekday")), String.valueOf(stringObjectMap.get("sum")));
        }
        return map;
    }
}
