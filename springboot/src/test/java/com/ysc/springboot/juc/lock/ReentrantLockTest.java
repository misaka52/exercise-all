package com.ysc.springboot.juc.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuanshancheng
 * @date 2021/3/28
 */
public class ReentrantLockTest {
    public static MyLock lock = new MyLock();
    public static void main(String[] args) throws InterruptedException {
        countDownLatch();
    }

    static void countDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            System.out.println("countDown 1 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("countDown 1 end");
        }).start();

        new Thread(() -> {
            System.out.println("countDown 2 start");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("countDown 2 end");
        }).start();

        new Thread(() -> {
            System.out.println("await 1 start");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("await 1 end");
        }).start();

        new Thread(() -> {
            System.out.println("await 2 start");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("await 2 end");
        }).start();
    }


    static void reentrantLock() throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock();
        reentrantLock.tryLock(1, TimeUnit.SECONDS);
        reentrantLock.lock();
        reentrantLock.unlock();
    }

    static class MyLock {
        synchronized static void fun() throws InterruptedException {
            System.out.println("fun start");
            Thread.sleep(2000);
            System.out.println("fun end");
        }

        public synchronized void fun2() throws InterruptedException {
            System.out.println("fun2 start");
            Thread.sleep(2000);
            System.out.println("fun2 end");
        }
    }


}
