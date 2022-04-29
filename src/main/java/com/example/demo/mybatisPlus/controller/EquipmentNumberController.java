package com.example.demo.mybatisPlus.controller;

import com.example.demo.mybatisPlus.model.EquipmentNumber;
import com.example.demo.mybatisPlus.service.EquipmentNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 学生信息 前端控制器
 * </p>
 *
 * @author trd
 * @since 2022-03-23
 */
@RestController
@RequestMapping("/equipment-number")
public class EquipmentNumberController {
    @Autowired(required = false)
    private EquipmentNumberService equipmentNumberService;

    /**
     * 查询工位
     *
     * @return 所有的工位
     */
    @RequestMapping("/getStation")
    public List<EquipmentNumber> getStation(@RequestBody(required = false) Map<String, Object> map) {
        return equipmentNumberService.getStation(map);
    }


    /**
     * @return 所有的班次
     */
    @RequestMapping("/getShift")
    public List<EquipmentNumber> getShift(@RequestBody(required = false) Map<String, Object> map) {
        return equipmentNumberService.getShift(map);
    }


    /**
     * @return 所有的生产线
     */
    @RequestMapping("/getProductionLine")
    public List<EquipmentNumber> getProductionLine(@RequestBody(required = false) Map<String, Object> map) {
        return equipmentNumberService.getProductionLine(map);
    }

    /**
     * @return 所有的车间
     */
    @RequestMapping("/getWorkShop")
    public List<EquipmentNumber> getWorkShop() {
        return equipmentNumberService.getWorkShop();
    }


    /**
     * 批量插入测试数据
     *
     * @param map
     * @return
     */
    @RequestMapping("/insert")
    public String insert(@RequestBody(required = false) Map<String, Object> map) {
        //插入数量 是否合格
        String number = (String) map.get("number");
        String qualified = (String) map.get("qualified");
        String workShop = (String) map.get("work_shop");
        String productionLine = (String) map.get("production_line");
        String station = (String) map.get("station");
        String shift = (String) map.get("shift");
        String date = (String) map.get("date");
        EquipmentNumber equipmentNumber = new EquipmentNumber();
        equipmentNumber.setQualified(Integer.parseInt(qualified));
        equipmentNumber.setDate(java.sql.Date.valueOf(date));
        equipmentNumber.setShift(shift);
        equipmentNumber.setStation(station);
        equipmentNumber.setWorkShop(workShop);
        equipmentNumber.setProductionLine(productionLine);
        //循环插入过程质量表
        for (int i = 0; i < Integer.parseInt(number); i++) {
            equipmentNumberService.save(equipmentNumber);
        }
        //插入工作时间表哦

        //插入休息时间表

        return "success";
    }

    //获取一段时间内的每一天
    public static void main(String[] args) throws ParseException {
        Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01");
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-03");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List list0 = new ArrayList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date0);
        while (cal.getTime().compareTo(date1) <= 0) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            list0.add(sdf.format(cal.getTime()));

            System.out.println(sdf.format(cal.getTime()));
        }

    }


    /**
     * @return 柱形
     */
    @RequestMapping("/listByModel")
    public Map<String, Object> listByModel(@RequestBody(required = false) Map<String, Object> map) throws ParseException {
        //把前端传过来的 ArrayList 数组转换成字符串
        StringBuilder sb = new StringBuilder();
        ArrayList<String> stations = (ArrayList<String>) map.get("station");
        for (String station : stations) {
            sb.append("'").append(station).append("',");
        }
        //值不为空就 去掉后面的，传值
        if (sb.length() > 0) {
            if (!"''".equals(sb.substring(0, sb.length() - 1))) {
                map.put("stations", sb.substring(0, sb.length() - 1));
            }

        }

        String startDate = (String) map.get("startDate");
        String endDate = (String) map.get("endDate");
        //定义一个list 存储开始时间到结束时间中的每一天数据
        List retList = new ArrayList();
        //获取开始时间到结束时间中的每一天 求每一天的oee
        Date date0 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List list0 = new ArrayList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date0);
        while (cal.getTime().compareTo(date1) <= 0) {
            list0.add(sdf.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH, 1);

        }

        System.out.println(list0.toString());
        //组装成  product：时间 加   当天所有 工位加 oee 的形式
        for (Object o : list0) {
            System.out.println(o.toString());
            map.put("date", o.toString());
            List<Map<String, Object>> list = equipmentNumberService.tableDataByModel(map);
            //新建map 存储数据
            Map<String, Object> retMap = new HashMap<>();
            for (Map<String, Object> stringObjectMap : list) {
                retMap.put("product", stringObjectMap.get("date"));
                retMap.put((String) stringObjectMap.get("station"), stringObjectMap.get("oee"));
            }
            retList.add(retMap);
        }

        List<String> list = equipmentNumberService.listByModel(map);
        System.out.println("list=" + list.toString());


        Map<String, Object> retMap = new HashMap<>();
        retMap.put("retList", retList);
        retMap.put("stationList", list);

        return retMap;
    }


    @RequestMapping("/tableDataByModel")
    public List<Map<String, Object>> tableDataByModel(@RequestBody(required = false) Map<String, Object> map) {
        //把前端传过来的 ArrayList 数组转换成字符串
        StringBuilder sb = new StringBuilder();
        ArrayList<String> stations = (ArrayList<String>) map.get("station");
        for (String station : stations) {
            sb.append("'").append(station).append("',");
        }
        //值不为空就 去掉后面的，传值
        if (sb.length() > 0) {
            if (!"''".equals(sb.substring(0, sb.length() - 1))) {
                map.put("stations", sb.substring(0, sb.length() - 1));
            }

        }
        return equipmentNumberService.tableDataByModel(map);

    }

    @RequestMapping("/stationList")
    public String stationList(@RequestBody(required = false) Map<String, Object> map) {
        //把前端传过来的 ArrayList 数组转换成字符串
        StringBuilder sb = new StringBuilder();
        ArrayList<String> stations = (ArrayList<String>) map.get("station");
        for (String station : stations) {
            sb.append("'").append(station).append("',");
        }
        //值不为空就 去掉后面的，传值
        if (sb.length() > 0) {
            map.put("stations", sb.substring(0, sb.length() - 1));
        }

        List<String> list = equipmentNumberService.listByModel(map);
        String retStr = "['product'";
        for (String s : list) {
            retStr = retStr + ",'" + s + "'";

        }
        retStr = retStr + "]";
        System.out.println("retStr=" + retStr);
        //  ['product', 'WC_CB_OP_271', 'WC_CB_OP_280', 'WC_CB_OP_290']
        return retStr;

    }


}

