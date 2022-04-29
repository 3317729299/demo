package com.example.demo.study.jdk8.stream;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @Description 作者
 * @Author trd
 * @Date 2022/4/29 14:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //用于后期的去重
public class Author {
    //id
    private Long id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //介绍
    private String introduction;
    //作品
    private List<Book> books;


}