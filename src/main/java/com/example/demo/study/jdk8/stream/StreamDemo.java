package com.example.demo.study.jdk8.stream;

import lombok.extern.slf4j.Slf4j;
import sun.security.util.Length;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * TODO Stream 流是对集合和数组进行操作
 * .stream 转换成流
 * <p>
 * 有终结操作才会调用流
 * <p>
 * 创建流
 * 单列集合 List set  多列集合 map
 * 数组 Array.asList.stream
 * 单列集合.stream
 * <p>
 * <p>
 * 函数式编程 传入一段代码
 *
 * @Author trd
 * @Date 2022/4/29 14:32
 */

@Slf4j
public class StreamDemo {
    private static List<Author> getAuthor() {

        Author author1 = new Author(1l, "蒙多", 33, "你要去哪里", null);
        Author author2 = new Author(1l, "亚索", 18, "面对疾风吧", null);
        Author author3 = new Author(1l, "女枪", 16, "我有两把枪", null);

        List<Book> books1 = new ArrayList<>(3);
        List<Book> books2 = new ArrayList<>(3);
        List<Book> books3 = new ArrayList<>(3);

        author1.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);

        Book book1 = new Book(1l, "刀的两侧是光明与黑暗", "哲学，爱情", 88, "用一把刀分割了黑暗");
        Book book2 = new Book(2l, "墨菲定律", "心理学", 66, "你害怕什么往往会发生什么");
        Book book3 = new Book(3l, "那风吹不到的地方", "心理学", 77, "幸存者偏差");
        Book book4 = new Book(4l, "爱在两级奔跑", "言情", 55, "转角遇见爱");
        Book book5 = new Book(5l, "人性的弱点", "心理学", 55, "遇上未来的自己");

        books1.add(book1);
        books1.add(book2);
        books2.add(book3);
        books2.add(book4);
        books3.add(book4);
        books3.add(book5);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author1, author2, author3));
        return authorList;


    }

    public static void main(String[] args) {
//       log.info(getAuthor().toString());
       /* List<Author> authors=getAuthor();
        authors.stream()//把集合转换成流
        .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));*/

      /*  Integer []  arr={1,2,3,3,4,7,2,5,6,7};
        Stream<Integer> stream =Arrays.stream(arr);
        Stream<Integer> stream2 =Stream.of(arr);
        stream.distinct()
                .filter(integer -> integer>2)
                .forEach(integer -> System.out.println(integer));*/

        //双列集合 转换成单列集合 再进行流操作
        Map<String, Integer> map = new HashMap<>(3);
        map.put("蜡笔小新", 19);
        map.put("黑子", 17);
        map.put("日向向阳", 16);


//        Stream<Map.Entry<String, Object>> stream1=map.entrySet().stream();
        Stream<Map.Entry<String, Integer>> entries = map.entrySet().stream();
        entries.filter(entry -> entry.getValue() > 16)
                .filter(entry -> entry.getKey().length() > 3)
                .filter(entry -> entry.getValue() == 19)
                .forEach(entry -> System.out.println(entry.getKey()));

    }

}