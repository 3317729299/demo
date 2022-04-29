package com.example.demo.study.jdk8;

/**
 * TODO
 *
 * @Author trd
 * @Date 2022/4/29 11:19
 */

public class Thread1 implements Runnable{
    @Override
    public void run() {
        System.out.println("心比天高，命比纸薄");
    }

    public static void main(String[] args) {
        //创建 Thread1 实例
        Thread1 thread1=new Thread1();
        //将实例放入 thread 的target创建thread 对象
        Thread thread = new Thread(thread1);
        //调用thread 的start 方法 启动线程
        thread.start();

        //lambda 实现
        new Thread(
                //括号代表其实现类  -> 调用实现类里面的东西
                ()-> {
            System.out.println("心比天高，命比纸薄");
        }
        ).start();
    }


}