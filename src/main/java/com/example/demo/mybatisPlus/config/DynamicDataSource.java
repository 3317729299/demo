package com.example.demo.mybatisPlus.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @description:类描述：自己写  不完善
 * @author:trd
 * @date:2022-03-14 12:44
 */
/*@Component
@Primary*/
public class DynamicDataSource implements DataSource, InitializingBean {//InitializingBean 初始化回调接口
    @Autowired
    DataSource dataSource1;
    @Autowired
    DataSource dataSource2;

    //当前使用的数据源标识
    public  static ThreadLocal<String> name=new ThreadLocal<>();  //ThreadLocal 线程安全
    @Override
    public Connection getConnection() throws SQLException {
        if(name.get().equals("W")){

        return dataSource1.getConnection();}
        else {
            return dataSource2.getConnection();
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    //重写afterPropertiesSet
    @Override
    public void afterPropertiesSet() throws Exception {
        //InitializingBean 初始化回调接口
        //默认写
        name.set("W");

    }

}
