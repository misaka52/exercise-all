package com.ysc.springboot.juc;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author yuanshancheng
 * @date 2021/3/7
 */
public class StructureTest {
    public static void main(String[] args) {
        linkedHashMap();
    }

    public static void linkedHashMap() {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        map.put(4, 1);
        System.out.println(map);
        map.get(2);
        System.out.println(map);
    }

    public static void hashMap() {
        HashMap map = new HashMap();
        map.get(1);
        map.put(1, 1);
    }
}
