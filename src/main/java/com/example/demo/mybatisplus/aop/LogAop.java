package com.example.demo.mybatisplus.aop;

import com.example.demo.mybatisplus.annotation.DS;
import com.example.demo.mybatisplus.config.Dynamic;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description:类描述： aop切面打印日志
 * @author:trd
 * @date:2022-03-14 15:51
 */
@Aspect
@Component
public class LogAop {
    private final static Logger logger = LoggerFactory.getLogger(LogAop.class);


    @Around("within(com.example.demo.mybatisplus.controller.*)")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        Object obj = null;
        try {
            obj = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        String name = pjp.getSignature().getName();

        long endTime = System.currentTimeMillis();
        logger.info("接受到请求{},参数为{},用时{}", name, args, endTime - startTime);
        return obj;
    }

    @Before("within(com.example.demo.mybatisplus.controller.*) && @annotation(ds)")
    public void before(DS ds) {
        String name = ds.value();

        logger.info("数据源标识{}", name);
        Dynamic.name.set(name);

    }


}
