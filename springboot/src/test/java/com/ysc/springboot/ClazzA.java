package com.ysc.springboot;

import org.apache.tomcat.util.digester.ArrayStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/1/8
 */
public class ClazzA {
    private final Object value;
    public ClazzA(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public static void main(String[] args) {
        System.out.println(List.class.isAssignableFrom(Collection.class));
        System.out.println(Collection.class.isAssignableFrom(List.class));
        System.out.println(Collection.class.isAssignableFrom(int.class));
        System.out.println(Integer.class.isAssignableFrom(int.class));
        System.out.println(int.class.isAssignableFrom(int.class));
        List<Integer> list = new ArrayList<>();
        System.out.println(list instanceof Collection);
        list = new ArrayStack<>();
        System.out.println(list instanceof ArrayList);
    }
}
