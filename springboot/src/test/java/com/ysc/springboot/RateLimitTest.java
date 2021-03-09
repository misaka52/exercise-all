package com.ysc.springboot;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static java.util.concurrent.ForkJoinPool.defaultForkJoinWorkerThreadFactory;

/**
 * @author yuanshancheng
 * @date 2021/1/30
 */
public class RateLimitTest {
    public static void main(String[] args) {
        ExecutorService executorService =
                new ForkJoinPool(100, defaultForkJoinWorkerThreadFactory, null, true);
        RateLimiter rateLimiter = RateLimiter.create(1);
        for (int i = 1; i < 10; ++i) {
            Double acquire = null;
            acquire = rateLimiter.acquire(i);
            System.out.println(i + "个消耗耗时" + acquire + "s");
//            executorService.submit(new MyRunnable(String.format("第%s个任务，耗时%f", i, acquire)));
        }
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
