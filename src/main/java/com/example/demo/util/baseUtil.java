package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @description:类描述：
 * @author:trd
 * @date:2022-03-23 09:42
 */

public class baseUtil {

    public static String todayOfWeek() {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar.get(Calendar.DAY_OF_WEEK)=" + calendar.get(Calendar.DAY_OF_WEEK));
        String todayOfWeek = weekDays[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        /*    System.out.println("今天是"+weekDays[calendar.get(Calendar.DAY_OF_WEEK)-1]);*/
        return todayOfWeek;

    }

    public static void main(String[] args) {
        System.out.println(baseUtil.todayOfWeek());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
        System.out.println(yesterday);

    }
    //按照每天时间分组 ,每天汇总,然后union 每周汇总
    //用map 存放数据  map.put("1",2)  map.get(1)


}
