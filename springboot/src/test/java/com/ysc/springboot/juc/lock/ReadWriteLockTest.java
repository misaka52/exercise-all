package com.ysc.springboot.juc.lock;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * @author yuanshancheng
 * @date 2021/3/6
 */
public class ReadWriteLockTest {
    public static void main(String[] args) throws InterruptedException {
//        readWriteLock();
//        countDownLatch();
//        cyclicBarrier();
        condition();
        System.out.println("end");
    }

    static void aqs() {
        AbstractQueuedSynchronizer aqs;
    }

    static void lockSupport() {
        LockSupport.park(1);
    }

    static void condition() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }

    static void readWriteLock() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock().lock();
        readWriteLock.readLock().lock();
    }

    static void reentrantLock() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
        try {
            reentrantLock.tryLock(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Condition condition = reentrantLock.newCondition();
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
