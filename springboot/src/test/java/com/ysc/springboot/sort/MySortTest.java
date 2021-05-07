package com.ysc.springboot.sort;

import java.util.Random;

/**
 * @author yuanshancheng
 * @date 2021/4/29
 */
public class MySortTest {
    public static void main(String[] args) {
        MySort mySort = new HeapSort();
        for (int i = 1; i < 1000; ++i) {
            int[] data = generate(i);
            mySort.sort(data);
            check(data);
        }
    }

    private static int[] generate(int n) {
        return generate(n, 1000);
    }

    private static int[] generate(int n, int max) {
        Random random = new Random();
        int[] data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = random.nextInt(max);
        }
        return data;
    }

    private static boolean check(int[] data) {
        int i = 1;
        while (i < data.length) {
            if (data[i] < data[i - 1]) {
                System.out.printf("not well:%d:%d,%d:%d",i - 1, data[i - 1], i, data[i]);
                return false;
            }
            ++i;
        }
        return true;
    }
}
