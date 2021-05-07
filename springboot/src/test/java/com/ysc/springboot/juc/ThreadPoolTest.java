package com.ysc.springboot.juc;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author yuanshancheng
 * @date 2021/3/5
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        schedule();
    }


    static void schedule() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(new MyRunnable(), 1, TimeUnit.SECONDS);
//        scheduledExecutorService.schedule(new MyRunnable(), 1, TimeUnit.SECONDS);
//        scheduledExecutorService.schedule(new MyRunnable(), 3, TimeUnit.SECONDS);
//        scheduledExecutorService.schedule(new MyRunnable(), 5, TimeUnit.SECONDS);
        // 固定周期任务
        scheduledExecutorService.scheduleAtFixedRate(new Thread(new MyRunnable(), "fix-rate"),
                1, 1, TimeUnit.SECONDS);
//        Thread.sleep(20000);
        System.out.println("\nstart fix-delay");
        // 固定延迟任务
//        scheduledExecutorService.scheduleWithFixedDelay(new Thread(new MyRunnable(), "fix-delay"),
//                2, 1, TimeUnit.SECONDS);
    }

    static void fixed() {
        Executors.newFixedThreadPool(10);
        Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Executors.newWorkStealingPool();
    }

    static void forkJoin(){
        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();
    }

    static class MyRunnable implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " " + new Date() + " exec");
            Thread.sleep(1000);
            System.out.println("end");
        }
    }
}
