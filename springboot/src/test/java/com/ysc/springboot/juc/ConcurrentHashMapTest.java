package com.ysc.springboot.juc;

import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

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
        ConcurrentLinkedQueue queue;
        StringBuffer stringBuffer;
        Vector vector;
        Hashtable hashtable;
        CopyOnWriteArrayList copyOnWriteArrayList;
    }
}
