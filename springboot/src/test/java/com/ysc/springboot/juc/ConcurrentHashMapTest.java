package com.ysc.springboot.juc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuanshancheng
 * @date 2021/3/6
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put(1, 2);
        map.get(1);
        map.size();
    }
}
