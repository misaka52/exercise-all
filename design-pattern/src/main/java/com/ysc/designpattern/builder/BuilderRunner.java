package com.ysc.designpattern.builder;

/**
 * 构建者模式
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class BuilderRunner {
    public static void main(String[] args) {
        Student s1 = new StudentBuilder().name("zhangsan").build();
        System.out.println(s1);

        Student s2 = new StudentBuilder().id(2).name("lisi").build();
        System.out.println(s2);
    }
}
