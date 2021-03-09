package com.ysc.springboot.jvm;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

/**
 * @author yuanshancheng
 * @date 2021/2/22
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();
        threadLocal.set(10);
        Thread t = Thread.currentThread();
        System.gc();
        threadLocal = null;
        System.gc();
        System.out.println("end");
    }
}
