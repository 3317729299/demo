package com.example.demo.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生信息
 * </p>
 *
 * @author trd
 * @since 2022-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pp_equipment_number")
public class EquipmentNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车间
     */
    private String workShop;

    /**
     * 生产线
     */
    private String productionLine;

    /**
     * 工位
     */
    private String station;

    /**
     * 班次
     */
    private String shift;

    /**
     * 序列号
     */
    private String serialNumber;

    /**
     * 时间
     */
    private Date date;

    /**
     * 是否合格
     */
    private Integer qualified;


}
