package com.example.demo.mybatisplus.service;

import com.example.demo.mybatisplus.model.Customercomplaint;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户抱怨表 服务类
 * </p>
 *
 * @author trd
 * @since 2022-03-22
 */
public interface CustomercomplaintService extends IService<Customercomplaint> {

    public List<Customercomplaint> listToday();

    List<Map<String, Object>> ListWeek();

    Map<String, Object> week();

}
