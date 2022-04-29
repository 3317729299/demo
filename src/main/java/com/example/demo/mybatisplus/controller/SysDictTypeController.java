package com.example.demo.mybatisplus.controller;


import com.example.demo.mybatisplus.model.SysDictType;
import com.example.demo.mybatisplus.service.SysDictTypeService;
import com.example.demo.util.ExcelUtil;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author trd
 * @since 2022-03-14
 */
@RestController
@RequestMapping("/sys-dict-type")

public class SysDictTypeController {
    @Resource
    private SysDictTypeService sysDictTypeService;

    /**
     * poi 导出excel
     * 1.在实体类需要导出的字段加上自定义注解 @ExportConfig
     *
     * @param request
     * @param response
     */
    @RequestMapping("/excel")
    public void getOne(HttpServletRequest request, HttpServletResponse response) {
        List<SysDictType> list = sysDictTypeService.list();
        ExcelUtil<SysDictType> export = new ExcelUtil<>(SysDictType.class, response, false);
        export.exportExcel(list, "字典类型表", -1);

    }


}

