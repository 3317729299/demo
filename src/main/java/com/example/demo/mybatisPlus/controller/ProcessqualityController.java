package com.example.demo.mybatisPlus.controller;


import com.example.demo.mybatisPlus.model.Processquality;
import com.example.demo.mybatisPlus.service.ProcessqualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 过程质量表 前端控制器
 * </p>
 *
 * @author trd
 * @since 2022-03-22
 */
@RestController
@RequestMapping("/processquality")
public class ProcessqualityController {
    @Autowired
    private ProcessqualityService processqualityService;

    @RequestMapping("/list")
    public List<Processquality> list(){

      return   processqualityService.list();
    }

    /**
     * 今天的质量问题
     * @return
     */
    @RequestMapping("/listToday")
    public List<Processquality> listToday(){

        return   processqualityService.listToday();
    }

    @RequestMapping("/week")
    @ResponseBody
    public Map<String,Object> week(){
        /*String jsonString = JSONObject.toJSONString( customercomplaintService.week());*/
        return  processqualityService.ListWeek();

    }

}

