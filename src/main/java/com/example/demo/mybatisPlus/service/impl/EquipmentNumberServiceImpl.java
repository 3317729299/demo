package com.example.demo.mybatisPlus.service.impl;

import com.example.demo.mybatisPlus.model.EquipmentNumber;
import com.example.demo.mybatisPlus.mapper.EquipmentNumberMapper;
import com.example.demo.mybatisPlus.service.EquipmentNumberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生信息 服务实现类
 * </p>
 *
 * @author trd
 * @since 2022-03-23
 */
@Service
public class EquipmentNumberServiceImpl extends ServiceImpl<EquipmentNumberMapper, EquipmentNumber> implements EquipmentNumberService {

    @Autowired(required = false)
    private EquipmentNumberMapper equipmentNumberMapper;

    @Override
    public List<EquipmentNumber> getStation(Map<String, Object> map) {
        return equipmentNumberMapper.getStation(map);
    }

    @Override
    public List<String> listByModel(Map<String, Object> map) {

        //新建一个list 存储 product + 满足条件的所有工位
        List<String> stationList = new ArrayList<>();
        List<EquipmentNumber> equipmentNumbers = equipmentNumberMapper.listByModel(map);
        stationList.add("product");
        for (EquipmentNumber equipmentNumber : equipmentNumbers) {
            stationList.add(equipmentNumber.getStation());
        }

        return stationList;
    }

    @Override
    public List<EquipmentNumber> getProductionLine(Map<String, Object> map) {
        return equipmentNumberMapper.getProductionLine(map);
    }

    @Override
    public List<EquipmentNumber> getShift(Map<String, Object> map) {
        return equipmentNumberMapper.getShift(map);
    }

    @Override
    public List<EquipmentNumber> getWorkShop() {
        return equipmentNumberMapper.getWorkShop();
    }

    @Override
    public List<Map<String, Object>> tableDataByModel(Map<String, Object> map) {
        return equipmentNumberMapper.tableDataByModel(map);
    }
}
