package com.ysc.springboot;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.SneakyThrows;

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
    public static void main(String[] args) throws InterruptedException {
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
//            threadLocal.remove();
            countDownLatch.countDown();
        }
    }

    static class Memory {
        private long[] m = new long[1024*1024];
    }
}
