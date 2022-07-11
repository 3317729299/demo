package com.example.demo.study.common;

import java.util.Arrays;

/**
 * TODO
 *
 * @Author trd
 * @Date 2022/5/12 15:23
 */

public class soutString {
    public static void main(String[] args) {
        String s1 = "HElloworld1234";
        char[] chs = s1.toCharArray();
        System.out.println(chs);
        System.out.println("前边加上字符输出的是地址："+chs);
        System.out.println("Arrays.toString(ch)以数组形式输出："+ Arrays.toString(chs));


    }


}