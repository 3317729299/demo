package com.example.demo.mybatisPlus.aspect;

import com.example.demo.mybatisPlus.annotation.DS;
import com.example.demo.mybatisPlus.config.Dynamic;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @description:类描述：
 * @author:trd
 * @date:2022-03-14 14:13
 */
/*@Component
@Aspect
@Slf4j*/
public class dynamicAspect {
    @Before("within(com.example.demo.mybatisPlus.controller.*) && @annotation(ds)")
    public void before(DS ds) {
        String name = ds.value();


        Dynamic.name.set(name);

    }
}
