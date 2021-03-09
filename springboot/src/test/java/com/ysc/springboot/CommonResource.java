package com.ysc.springboot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuanshancheng
 * @date 2021/2/6
 */
public class CommonResource {
    public static ExecutorService executorService = Executors.newWorkStealingPool();
}
