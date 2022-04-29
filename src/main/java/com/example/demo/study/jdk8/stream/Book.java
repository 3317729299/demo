package com.example.demo.study.jdk8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * TODO
 * @Description 书
 * @Author trd
 * @Date 2022/4/29 14:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //用于后期的去重
public class Book {

    //id
    private Long id;
    //书名
    private String name;
    //分类
    private  String category;
    //评分
    private Integer score;
    //简介
    private String introduction;

}