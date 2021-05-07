package com.ysc.springboot;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author yuanshancheng
 * @date 2021/1/28
 */
public class ThreadLocalTest {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static final Executor executorService = Executors.newWorkStealingPool();
    private static final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(100);
    private static final int hashIncrement = 0x61c88647;
    public static void main(String[] args) throws InterruptedException {
//        hashConflict();
//        fun();
        prefectHash();
    }

    static void fun() {
        Thread thread = Thread.currentThread();
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(1);
        System.gc();
        System.out.println(threadLocal.get());
        threadLocal = null;
        System.gc();
        ThreadLocal threadLocal2 = new ThreadLocal();
        threadLocal2.set(2);
        System.out.println(threadLocal2.get());
    }

    static void hashConflict() {
        ThreadLocal[] threadLocals = new ThreadLocal[16 * 2];
        for (int i = 0; i < 16; ++i) {
            threadLocals[i] = new ThreadLocal();
            threadLocals[i].set(i);
            if (i >= 3) {
                threadLocals[i].remove();
            }
        }
        ThreadLocal tmp = new ThreadLocal();
        tmp.set(16);
        for (int i = 0; i < 15; ++i) {
            ThreadLocal<Integer> tmp2 = new ThreadLocal<>();
            tmp2.set(1);
            tmp2.remove();
        }
        ThreadLocal<Integer> tmp3 = new ThreadLocal<>();
        tmp3.set(17);

        tmp.remove();
        System.out.println(tmp3.get());
    }

    // 完美散列
    static void prefectHash() {
        int cap = 8;
        int hash = 0;
        HashSet<Integer> hashSet = new HashSet<>(cap * 2);
        for (int i = 0; i < 20; ++i) {
            cap *= 2;
            for (int k = 0; k < cap; ++k) {
                hashSet.add(hash & (cap - 1));
                hash += hashIncrement;
            }
            System.out.print(cap + ":");
            if (hashSet.size() == cap) {
                System.out.println("无冲突");
            } else {
                System.out.println("发生重入，实际数量：" + hashSet.size());
            }
        }
    }

    static void test() throws InterruptedException {
        int count = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; ++i) {
//            executorService.execute(new MyThread(i, countDownLatch));
            new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                }
            }.start();
        }
        countDownLatch.await();
        System.out.println("success");
    }

    static class MyThread implements Runnable {
        int id;
        volatile Integer flag;
        CountDownLatch countDownLatch;
        public MyThread(int id, CountDownLatch countDownLatch) {
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        @SneakyThrows
        @Override
        public void run() {
            if (ThreadLocalRandom.current().nextBoolean()) {
                System.out.println(Thread.currentThread());
                threadLocal.set(2);
                flag = 2;
            } else {
//                threadLocal.set(false);
//                flag = false;
            }
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            boolean match = Objects.equals(threadLocal.get(), flag);
            if (!match) {
//                System.out.println(id + ":" + threadLocal.get() + ":" + flag);
            }
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
            threadLocal.remove();
            countDownLatch.countDown();
        }
    }

    static class Memory {
        private long[] m = new long[1024*1024];
    }
}
