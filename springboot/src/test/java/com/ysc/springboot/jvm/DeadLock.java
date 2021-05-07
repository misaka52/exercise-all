package com.ysc.springboot.jvm;

/**
 * @author yuanshancheng
 * @date 2021/5/5
 */
public class DeadLock {
    public static void main(String[] args) {
        deadLock();
    }

    static void deadLock() {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("a-b lock success");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println("b-a lock success");
                }
            }
        }).start();
    }
}
