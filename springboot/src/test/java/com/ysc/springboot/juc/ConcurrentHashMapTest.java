package com.ysc.springboot.juc;

import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author yuanshancheng
 * @date 2021/3/6
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
//        int2();
        chm();
    }

    static void int2() {
        System.out.println(-1 >>> 31);
        int i = Integer.MIN_VALUE >> 16;
        System.out.println(i);
        System.out.println(Integer.numberOfLeadingZeros(i));
        System.out.println(Integer.numberOfLeadingZeros(4));
        System.out.println(Integer.numberOfLeadingZeros(5));
        System.out.println(Integer.numberOfLeadingZeros(6));
        System.out.println(Integer.numberOfLeadingZeros(7));
    }

    static void chm() {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
//        for (int i = 1; i <= 10; ++i) {
//            if (i >= 9) {
//                System.out.println("begin");
//            }
//            map.put(i * 16, 1);
//        }
        map.put(1, 1);
        map.computeIfAbsent(1, k -> 1);
        System.out.println(map);
        map.computeIfAbsent(1, k -> 2);
        System.out.println(map);
        map.size();
    }

    static void longArrer() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(10);

    }
}
