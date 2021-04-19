package com.ysc.springboot;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ForkJoinPool.defaultForkJoinWorkerThreadFactory;

/**
 * @author yuanshancheng
 * @date 2021/1/30
 */
public class RateLimitTest {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(5, 1, TimeUnit.SECONDS);
//        RateLimiter rateLimiter = RateLimiter.create(5);
        for (int i = 0; i < 5; ++i) {
            System.out.println(rateLimiter.acquire());
        }
        Thread.sleep(1000);
        for (int i = 0; i < 5; ++i) {
            System.out.println(rateLimiter.acquire());
        }
        System.out.println("end");
    }

    static class MyRunnable implements Runnable {
        private String str;
        public MyRunnable(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            System.out.println(simpleDateFormat.format(new Date()) + " " + str);
        }
    }
}
