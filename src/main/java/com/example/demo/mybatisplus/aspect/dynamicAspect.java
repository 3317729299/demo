package com.example.demo.mybatisplus.aspect;

import com.example.demo.mybatisplus.annotation.DS;
import com.example.demo.mybatisplus.config.Dynamic;
import org.aspectj.lang.annotation.Before;

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
