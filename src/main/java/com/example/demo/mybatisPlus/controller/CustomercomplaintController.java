package com.example.demo.mybatisPlus.controller;




import com.alibaba.fastjson.JSONObject;
import com.example.demo.mybatisPlus.model.Customercomplaint;
import com.example.demo.mybatisPlus.service.CustomercomplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户抱怨表 前端控制器
 * </p>
 *
 * @author trd
 * @since 2022-03-22
 */
@Controller
@RequestMapping("/customercomplaint")

public class CustomercomplaintController {
    @Autowired
    private CustomercomplaintService customercomplaintService;
    @RequestMapping("/list")
    @ResponseBody
    public List<Customercomplaint> list(){
        return  customercomplaintService.list();

    }

    /**
     * 当天的抱怨情况
     * @return
     */
    @RequestMapping("/listToday")
    @ResponseBody
    public List<Customercomplaint> listToday(){
        return  customercomplaintService.listToday();

    }


    /**
     * 本周的抱怨情况
     * @return
     */
    @RequestMapping("/listWeek")
    @ResponseBody
    public List<Map<String,Object>> listWeek(){
        return  customercomplaintService.ListWeek();

    }

    /**
     * 本周的抱怨情况
     * @return
     */
    @RequestMapping("/week")
    @ResponseBody
    public Map<String,Object> week(){
        /*String jsonString = JSONObject.toJSONString( customercomplaintService.week());*/
        return  customercomplaintService.week();

    }

    /**
     * 跳转到报表页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "echarts";
    }


    /**
     * 跳转到报表页面
     * @return
     */
    @RequestMapping("/oEE")
    public String oEE(){
        return "oEE";
    }

    /**
     * 跳转到报表页面
     * @return
     */
    @RequestMapping("/bootstrap")
    public String bootstrap(){
        return "bootstrap";
    }


    /**
     * 跳转到报表页面
     * @return
     */
    @RequestMapping("/hanThink")
    public String layui(){
        return "HanThink";
    }

}

