package com.example.demo.mybatisPlus.service;

import com.example.demo.mybatisPlus.model.Processquality;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 过程质量表 服务类
 * </p>
 *
 * @author trd
 * @since 2022-03-22
 */
public interface ProcessqualityService extends IService<Processquality> {

   Map<String, Object> ListWeek();

   public List<Processquality> listToday ();

}
