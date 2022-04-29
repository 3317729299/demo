package com.example.demo.mybatisplus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:类描述：
 * @author:trd
 * @date:2022-03-14 13:39
 */
//继承 AbstractRoutingDataSource
//
/*@Component
@Primary*/
public class Dynamic extends AbstractRoutingDataSource {


    @Autowired
    DataSource dataSource1;
    @Autowired
    DataSource dataSource2;
    //当前使用的数据源标识
    public static ThreadLocal<String> name = new ThreadLocal<>();  //ThreadLocal 线程安全

    //返回数据源
    @Override
    protected Object determineCurrentLookupKey() {
        return name.get();
    }

    //resolveDataSouce 负责做最终的切换
    @Override
    public void afterPropertiesSet() {
        //为targetdataSource 初始化所有的数据源
        Map<Object, Object> targetdataSource = new HashMap<>();
        targetdataSource.put("W", dataSource1);
        targetdataSource.put("R", dataSource2);
        super.setTargetDataSources(targetdataSource);
        //为defaultTargetDataSource 设置默认的数据源
        super.setDefaultTargetDataSource(dataSource1);
        super.afterPropertiesSet();
    }
}
