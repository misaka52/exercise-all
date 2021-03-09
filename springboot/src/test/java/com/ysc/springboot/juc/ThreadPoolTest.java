package com.ysc.springboot.juc;

import java.util.concurrent.*;

/**
 * @author yuanshancheng
 * @date 2021/3/5
 */
public class ThreadPoolTest {
    public static void main(String[] args) {

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
}
