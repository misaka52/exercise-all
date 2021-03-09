package com.ysc.springboot.juc.lock;

import lombok.SneakyThrows;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author yuanshancheng
 * @date 2021/3/6
 */
public class ReadWriteLockTest {
    public static void main(String[] args) throws InterruptedException {
//        countDownLatch();
        cyclicBarrier();
        System.out.println("end");
    }

    static void readWriteLock() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock();
    }

    static void reentrantLock() {
        ReentrantLock reentrantLock;
    }

    static void stampedLock() {
        StampedLock stampedLock = new StampedLock();
    }

    static void countDownLatch() throws InterruptedException {
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; ++i) {
            new Thread() {
                @SneakyThrows
                @Override
                public void run() {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " ing");
                countDownLatch.countDown();
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + " finish");
            }}.start();
        }
    }

    static void cyclicBarrier() throws InterruptedException {
        int count = 10;
        CyclicBarrier barrier = new CyclicBarrier(count);

        for (int i = 0; i < count; ++i) {
            new Thread() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                    System.out.println(Thread.currentThread().getName() + " 正在写入数据");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " finish");
                }
            }.start();
        }
    }
}
