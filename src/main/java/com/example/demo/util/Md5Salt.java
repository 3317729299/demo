package com.example.demo.util;

import java.util.Random;

/**
 * @description:类描述：md5 盐值加密
 * @author:trd
 * @date:2022-03-14 23:24
 */

public class Md5Salt {


    //length用户要求，随机产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
