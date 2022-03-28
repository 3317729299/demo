package com.example.demo.mybatisPlus.mapper;

import com.example.demo.mybatisPlus.model.EquipmentNumber;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author trd
 * @since 2022-03-23
 */
public interface EquipmentNumberMapper extends BaseMapper<EquipmentNumber> {
    /**
     *
     * @return 所有工号
     */
    public List<EquipmentNumber> getStation(Map<String, Object> map);
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

    /**
     *
     * @param map
     * @return
     */
    public List<EquipmentNumber> listByModel(Map<String,Object> map);

    public List<Map<String,Object>>  tableDataByModel( Map<String,Object> map);



}
