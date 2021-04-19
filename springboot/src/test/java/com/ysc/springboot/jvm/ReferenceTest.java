package com.ysc.springboot.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author yuanshancheng
 * @date 2021/3/23
 */
public class ReferenceTest {
    public static void main(String[] args) {
        Demo demo = new Demo(new Object());
    }

    static class Demo extends WeakReference<Object> {

        public Demo(Object referent) {
            super(referent);
        }
    }
}
