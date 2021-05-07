package com.ysc.springboot.jvm;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author yuanshancheng
 * @date 2021/4/13
 */
public class MemoryTest {

    private static final int ONE_M = 1024 * 1024;
    public static void main(String[] args) {
//        memory();
//        metaspaceOOM();
        new Thread(() -> {
            deadLoop();
        }).start();
        deadLoop();
    }

    static void deadLoop() {
        int i = 1;
        while (true) {
            i += 1;
            i *= i;
        }
    }

    static void metaspaceOOM() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MemoryTest.class);
            enhancer.setCallbackTypes(new Class[]{Dispatcher.class, MethodInterceptor.class});
            enhancer.setCallbackFilter(new CallbackFilter() {
                @Override
                public int accept(Method method) {
                    return 1;
                }
            });

            Class clazz = enhancer.createClass();
        }
    }

    static void memory() {
        Map map = System.getProperties();
        for (int i = 0; i < 1000000; ++i) {
            map.put(i, "fdsafd中文");
        }
    }

    interface Interf {
        void fun();
    }

    static class Impl implements Interf {

        @Override
        public void fun() {

        }
    }
}
