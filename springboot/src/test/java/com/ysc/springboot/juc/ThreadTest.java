package com.ysc.springboot.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yuanshancheng
 * @date 2021/3/3
 */
public class ThreadTest {
    private volatile static boolean notStart = true;
    private volatile static boolean notEnd = true;
    public static void main(String[] args) throws Exception {
//        threadPriority();
        thread();
    }

    static void thread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; ++i) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.interrupt();
        for (int i = 0; i < 200; ++i) {
            System.out.println("main");
            if (i == 100) {
                thread.join();
            }
        }

    }

    public static void threadPriority() throws InterruptedException {
        int threadCount = 10;
        AtomicInteger[] atomicIntegers = new AtomicInteger[threadCount];
        for (int i = 0; i < threadCount; ++i) {
            atomicIntegers[i] = new AtomicInteger();
            Thread thread = new MyThread("thread" + i, atomicIntegers[i]);
            thread.setPriority(i >= 5 ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY);
            thread.start();
        }
        notStart = false;
        Thread.sleep(5000);
        notEnd = false;
        for (int i = 0; i < threadCount; ++i) {
            System.out.println(i + ":" + atomicIntegers[i].get());
        }
    }

    static class MyThread extends Thread {
        private AtomicInteger num;

        public MyThread(String name, AtomicInteger num) {
            super(name);
            this.num = num;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                Thread.yield();
                num.incrementAndGet();
            }
        }
    }
}
