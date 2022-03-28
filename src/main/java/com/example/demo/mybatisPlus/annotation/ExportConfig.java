package com.example.demo.mybatisPlus.annotation;


import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于Excel导出列名属性配置
 *
 * @author chenj.
 * @date 2017/11/30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExportConfig {


    /**
     * @return 表头显示名(如 ： id字段显示为 " 编号 ") 默认为字段名.
     */
    String name() default "field";

    /**
     * @return 配置列的名称, 对应A, B, C, D....
     */
    String column();

    /**
     * @return 单元格宽度 默认-1(自动计算列宽)
     */
    short width() default -1;

    /**
     * @return 当前单元格的字体颜色 (默认 HSSFColor.BLACK.index)
     */
    short color() default HSSFColor.BLACK.index;

    /**
     * @return 当前单元格的字体大小 (默认 10px)
     */
    short size() default 10;

    /**
     * @return 当前单元格的背景颜色 (默认 HSSFColor.WHITE.index)
     */
    short bgColor() default HSSFColor.WHITE.index;

    /**
     * @return 设置单元格水平对齐方式 (默认 CellStyle.ALIGN_CENTER)
     * 对齐方式包括：CellStyle.ALIGN_CENTER     水平居中
     * CellStyle.ALIGN_LEFT       水平居左
     * CellStyle.ALIGN_RIGHT      水平居右
     * CellStyle.ALIGN_JUSTIFY    适应单元格内容的宽度
     * ...
     */
    short align() default CellStyle.ALIGN_CENTER;

    /**
     * 将单元格的值替换为当前配置的值：<br>
     * 应用场景: <br>
     * 密码字段导出为："******"
     *
     * @return 默认true
     */
    String replace() default "";

    /**
     * @return 提示信息
     */
    String prompt() default "";

    /**
     * @return 设置只能选择不能输入的列内容.
     */
    String[] combo() default {};

    /**
     * @return 是否导出数据, 应对需求:有时我们需要导出一份模板,这是标题需要但内容需要用户手工填写.
     */
    boolean isExport() default true;

}
