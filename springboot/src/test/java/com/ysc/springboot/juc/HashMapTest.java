package com.ysc.springboot.juc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 * @author yuanshancheng
 * @date 2021/3/6
 */
public class HashMapTest {
    private static final int MAX = 10000;
    private static HashMap<Integer, Integer> map = new HashMap<>(MAX * 2);
    public static void main(String[] args) throws InterruptedException {
        multiAdd();
    }

    static void multiAdd() throws InterruptedException {
        for (int i = 0; i < 10; ++i) {
            new MyThread(i, MAX, 10).start();
        }
        Thread.sleep(1000);
        System.out.println(map.size());
    }

    static class MyThread extends Thread {
        int num;
        int max;
        int step;
        public MyThread(int num, int max, int step) {
            this.num = num;
            this.max = max;
            this.step = step;
        }

        @Override
        public void run() {
            for (int i = num; i <= max; i+=step) {
                map.put(i, i);
            }
            System.out.println(num + " end");
        }
    }

    static void fun() {
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
