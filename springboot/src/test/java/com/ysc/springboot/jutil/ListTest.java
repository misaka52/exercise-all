package com.ysc.springboot.jutil;

import java.util.ArrayList;

/**
 * @author yuanshancheng
 * @date 2021/5/4
 */
public class ListTest {
    public static void main(String[] args) {

    }
    static void fun() {
        ArrayList<Integer> list = new ArrayList<Integer>(){{
            add(1);
            add(2);
        }};
        list.add(1, 2);
    }
}
