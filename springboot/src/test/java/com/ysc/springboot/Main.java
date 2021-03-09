package com.ysc.springboot;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author yuanshancheng
 * @date 2021/2/22
 */
public class Main {
    private static Unsafe unsafe;

    // 为了获取 field 的 offset
    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        MemoryUse memoryUse = new MemoryUse();
//        for (Field field : memoryUse.getClass().getDeclaredFields()) {
//            // 查看域起始位置，单位字节，可用来判断字段大小
//            System.out.println(field.getName() + ":" + unsafe.objectFieldOffset(field));
//        }
    }

    static interface defInter {
        default void print() {
            System.out.println("interface default fun");
        }
        static void print2() {
            System.out.println("interface default fun2");
        }
    }

    static class MemoryUse {
        int int0;
        long long0;
        short short0;
        String str = "hello world";
        String str2 = "hello";
        String str3 = "end";
    }
}
