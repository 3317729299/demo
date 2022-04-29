package com.example.demo.mybatisplus.service.impl;

import com.example.demo.mybatisplus.model.WorkTime;
import com.example.demo.mybatisplus.mapper.WorkTimeMapper;
import com.example.demo.mybatisplus.service.WorkTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工作时间表 服务实现类
 * </p>
 *
 * @author trd
 * @since 2022-03-23
 */
@Service
public class WorkTimeServiceImpl extends ServiceImpl<WorkTimeMapper, WorkTime> implements WorkTimeService {

}
