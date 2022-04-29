package com.example.demo.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MybatisPlusConfig {


    public static void main(String[] args) {

        // 1.代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //2. 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("trd");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setFileOverride(true); //重新生成时文件是否覆盖

        gc.setServiceName("%sService"); //去掉Service接口的首字母

        gc.setIdType(IdType.ID_WORKER_STR); //主键策略

        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型

        gc.setSwagger2(false);//开启Swagger2模式

        mpg.setGlobalConfig(gc);
        //3.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/hanthink?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=CTT");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setSchemaName("plus_demo");*/
        mpg.setDataSource(dsc);
        //4包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent("com.example.demo.mybatisPlus");

        pc.setController("controller");
        pc.setEntity("model");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        TemplateConfig templateConfig = new TemplateConfig();
        /*templateConfig.setController("templates/controller.java.vm");*/
        /* templateConfig.setXml("templates/mapper.xml.vm");*/

        mpg.setTemplate(templateConfig);

        //5.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("pp_work_time");//对哪张表生成
        strategyConfig.setControllerMappingHyphenStyle(false);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategyConfig.setTablePrefix("pp_");//生成实体时去掉表前缀
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);//lombok模型@Accessors(chain=true)setter链式操作
        strategyConfig.setRestControllerStyle(true);//restful api风格控制器
        strategyConfig.setControllerMappingHyphenStyle(true);//url中驼峰转连字符

        mpg.setStrategy(strategyConfig);
        //6.执行
        mpg.execute();

    }
}
