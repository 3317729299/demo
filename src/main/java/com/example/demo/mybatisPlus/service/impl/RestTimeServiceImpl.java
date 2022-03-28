package com.example.demo.mybatisPlus.service.impl;

import com.example.demo.mybatisPlus.model.RestTime;
import com.example.demo.mybatisPlus.mapper.RestTimeMapper;
import com.example.demo.mybatisPlus.service.RestTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 计划休息时间表 服务实现类
 * </p>
 *
 * @author trd
 * @since 2022-03-23
 */
@Service
public class RestTimeServiceImpl extends ServiceImpl<RestTimeMapper, RestTime> implements RestTimeService {

}
