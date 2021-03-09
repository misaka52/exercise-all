package com.ysc.springboot.juc;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author yuanshancheng
 * @date 2021/3/6
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "111");
        map.put(2, "111");
        map.put(3, "111");
        map.remove(1);
        for (String s : map.values()) {
            System.out.println(s);
            map.put(2, "222");
//            map.remove(2);
            map.put(4, "111");
        }
    }
}
