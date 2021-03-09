package com.ysc.springboot.juc.lock;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

/**
 * @author yuanshancheng
 * @date 2021/3/8
 */
public class CyclicPrint {
    public static void main(String[] args) throws Exception {
//        cyclicPrintSem();
        cyclicPrintH2O();
    }

    // 连续打印水元素，H2O，保证三个（两H一O）成队出现
    static void cyclicPrintH2O() throws InterruptedException {
        CyclicPrintH2O cyclicPrintH2O = new CyclicPrintH2O();
        for (int i = 0; i < 100; ++i) {
            new Thread(() -> {
                try {
                    cyclicPrintH2O.printH();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    cyclicPrintH2O.printO();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(2000);
    }

    static class CyclicPrintH2O {
        private Semaphore semaphoreH = new Semaphore(2);
        private Semaphore semaphoreO = new Semaphore(2);
        public void printH() throws InterruptedException {
            semaphoreH.acquire();
            System.out.print("H");
            semaphoreO.release();
        }

        public void printO() throws InterruptedException {
            semaphoreO.acquire(2);
            System.out.print("O");
            semaphoreH.release(2);
        }
    }

    // 线程循环打印。信号量方式实现
    static void cyclicPrintSem() {
        int count = 5;
        Semaphore[] semaphores = new Semaphore[count];
        for (int i = 0; i < count; ++i) {
            semaphores[i] = new Semaphore(0);
        }
        semaphores[0].release();
        for (int i = 0; i < count; ++i) {
            new Thread(new MyRunnable(i + 1, semaphores[i], semaphores[(i + 1) % count])).start();
        }
    }

    static class MyRunnable implements Runnable {
        private final int num;
        private final Semaphore currSemaphore;
        private final Semaphore nextSemaphore;
        public MyRunnable(int num, Semaphore currSemaphore, Semaphore nextSemaphore) {
            this.num = num;
            this.currSemaphore = currSemaphore;
            this.nextSemaphore = nextSemaphore;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; ++i) {
                currSemaphore.acquire();
                System.out.println(num + "-" + i + " print");
                nextSemaphore.release();
            }
        }
    }
}
