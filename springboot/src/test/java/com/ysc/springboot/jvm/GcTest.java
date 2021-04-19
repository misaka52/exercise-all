package com.ysc.springboot.jvm;

import java.util.Random;

/**
 * @author yuanshancheng
 * @date 2021/3/23
 */
public class GcTest {
    private static int _1M = 1024 * 1024;
    public static void main(String[] args) throws InterruptedException {
        byte[] o1 = new byte[2 * _1M];
        o1 = null;
        byte[] o2 = new byte[50 * _1M];
        int[] is = new int[_1M];
        long[] ls = new long[_1M];
        double[] ds = new double[_1M];
        byte[][] tmp_o_2 = new byte[5][_1M];
        Double[] ds2 = new Double[10];
//        byte[] o3 = new byte[5 * _1M];
//        byte[] o3 = new byte[6 * _1M];
//        byte[] o4 = new byte[2 * _1M];
//        byte[] o4 = new byte[30 * _1M];
//        System.gc();
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
        public BigInstance(int a) {
            data = new byte[a * _1M];
        }
    }
}
