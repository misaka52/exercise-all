package com.ysc.springboot.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuanshancheng
 * @date 2021/2/28
 */
public class Example {
    public static void main(String[] args) {
        System.out.println("start");
        A a = new A();
        A.getInstance();
        System.out.println("end");
    }

    static class A {
        static {
            System.out.println("a static");
        }
        public A() {
            System.out.println("a private");
        }
        static class B {
            static {
                System.out.println("b static");
            }
            public B() {
                System.out.println("b private");
            }
            public static A instance = new A();
        }
        public static A getInstance() {
            return B.instance;
        }
    }
}
