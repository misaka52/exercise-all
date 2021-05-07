package com.ysc.springboot.juc;

import lombok.SneakyThrows;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanshancheng
 * @date 2021/5/7
 */
public class DelayQueueTest {
    static class MyDate implements Delayed {
        long invalidTime;
        public MyDate(int validTime) {
            this.invalidTime = System.currentTimeMillis() + validTime * 1000;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(invalidTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == this) {
                return 0;
            }
            if (o instanceof MyDate) {
                long diff = this.invalidTime - ((MyDate) o).invalidTime;
                if (diff > 0) {
                    return 1;
                } else if (diff == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return "MyDate(invalidTime = " + invalidTime + ";isValid=" + (System.currentTimeMillis() <= invalidTime);
        }
    }

    static class Producer implements Runnable {
        private final DelayQueue<MyDate> delayQueue;
        public Producer(DelayQueue delayQueue) {
            this.delayQueue = delayQueue;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                int random = ThreadLocalRandom.current().nextInt(1, 4);
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " put " + random);
                delayQueue.put(new MyDate(random));

                Thread.sleep(1000);
            }
        }
    }

    static class Consumer implements Runnable {
        private final DelayQueue<MyDate> delayQueue;
        public Consumer(DelayQueue delayQueue) {
            this.delayQueue = delayQueue;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                MyDate myDate = delayQueue.take();
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " take " + myDate);
            }
        }
    }

    public static void main(String[] args) {
        DelayQueue<MyDate> delayQueue = new DelayQueue<>();

        Thread thread1 = new Thread(new Producer(delayQueue), "producer-1");
        Thread thread2 = new Thread(new Consumer(delayQueue), "consumer-1");

        thread1.start();
        thread2.start();
    }
}
