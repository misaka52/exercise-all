package com.ysc.springboot.jvm;

import java.util.Random;

/**
 * @author yuanshancheng
 * @date 2021/3/23
 */
public class GcTest {
    private static int _1M = 1024 * 1024;
    public static void main(String[] args) throws InterruptedException {
        escape();
//        optimize();
    }

    static void optimize() {
        for (int i = 0; i < 10; ++i) {
            loop();
        }
    }

    static void loop() {
        long start = System.currentTimeMillis();
        int k = 0;
        for (int i = 0; i < 1000000000; ++i) {
            k += i;
        }
        System.out.println((System.currentTimeMillis() - start));
    }

    static void create() {
        BigInstance bigInstance = new BigInstance();
    }

    static void create2(BigInstance instance) {
        BigInstance bigInstance = instance;
    }

    private static BigInstance bigInstance;
    static void create3(BigInstance instance) {
        bigInstance = instance;
    }

    static void escape() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; ++i) {
            //1 分配在栈上
//            create();
            //2 分配在栈上
            create2(new BigInstance());
            //3 分配在堆上
//            create3(new BigInstance());
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    static void fun() throws InterruptedException {
        byte[] o1 = new byte[2 * _1M];
        o1 = null;
        byte[] o2 = new byte[50 * _1M];
        int[] is = new int[_1M];
        long[] ls = new long[_1M];
        double[] ds = new double[_1M];
        byte[][] tmp_o_2 = new byte[5][_1M];
        Double[] ds2 = new Double[10];
        System.out.println("gc 1");
        Random random = new Random();
        while (true) {
            byte[] tmp = new byte[_1M];
            byte[][] tmp2 = new byte[5][_1M];
//            BigInstance instance = new BigInstance(random.nextInt(10));
            Thread.sleep(1000);
        }
    }

    private static class BigInstance {
        private byte[] data;
        public BigInstance() {}
        public BigInstance(int a) {
            data = new byte[a * _1M];
        }
    }
}
