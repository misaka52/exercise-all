package com.ysc.springboot.juc.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author yuanshancheng
 * @date 2021/3/8
 */
class H2O {

    private Semaphore hSem = new Semaphore(2);
    private Semaphore oSem = new Semaphore(2);
    private CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        hSem.release(2);
        oSem.release(2);
    });
    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException, BrokenBarrierException {
        hSem.acquire();

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        barrier.await();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException,BrokenBarrierException {
        oSem.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        barrier.await();
    }
}