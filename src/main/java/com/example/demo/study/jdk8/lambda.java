package com.example.demo.study.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * TODO Lambda 表达式是 对只有一个抽象方法的匿名内部类的简化写法
 * 他可以对某些匿名内部类的写法进行简化
 * 它是函数式编程思想的一个重要体现
 * 让我们不用关心是什么对象
 * 而是关注我们对数据进行了什么操作
 * <p>
 * 接口的匿名内部类 只有一个抽象方法 就可以简化
 * <p>
 * 只关心 参数和操作
 * (String i) -> return  String.valueOf(i+"hello")
 * i -> i +"hello"
 *
 * @Author trd
 * @Date 2022/4/29 11:35
 */
//ctrl alt v 自动补全变量
//ctrl shift 空格 提示构造方法

@Slf4j
public class lambda {
    public static void main(String[] args) {


        new Thread(
                // 匿名内部类
                () -> {
                    System.out.println("hello");
                }).start();

        //lambda实现

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("world");
            }
        }).start();


        // alt +回车
        int c = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int a, int b) {
                return a + b;
            }
        });


        log.info(String.valueOf("c=" + c));

         printNum(i->i%2==0);
       log.info(typeCover( i->Integer.parseInt(i)).toString());  ;

         log.info(typeCover( i->i+"abc"));
         foreachArr(i -> log.info(String.valueOf(i)));
    }

    public static  <R> R typeCover(Function<String,R> function){
        String str="1235";
        R result= function.apply(str);
        return  result;
        
    }

    public static void  foreachArr(IntConsumer consumer){
        int []  arr={1,2,3,4,5,6,7,8,9,10}   ;
        for (int i:arr
             ) {
            consumer.accept(i);
            
        }
        
    }

    private static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }


    private static void printNum(IntPredicate predicate) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i : arr
        ) {
            if (predicate.test(i)) {
                log.info(String.valueOf(i));
            }

        }

    }


}