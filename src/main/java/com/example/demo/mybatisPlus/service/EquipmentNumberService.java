package com.example.demo.mybatisPlus.service;

import com.example.demo.mybatisPlus.model.EquipmentNumber;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生信息 服务类
 * </p>
 *
 * @author trd
 * @since 2022-03-23
 */
public interface EquipmentNumberService extends IService<EquipmentNumber> {

    public List<EquipmentNumber> getStation(Map<String, Object> map);

    public List<String> listByModel(Map<String,Object> map);

    /**
     *
     * @return 所有生产线
     */
    public List<EquipmentNumber> getProductionLine(Map<String, Object> map);
    /**
     *
     * @return 所有班次
     */
    public List<EquipmentNumber> getShift(Map<String, Object> map);
    /**
     *
     * @return 所有车间
     */
    public List<EquipmentNumber> getWorkShop();

    public List<Map<String,Object>>  tableDataByModel(Map<String,Object> map);

}
