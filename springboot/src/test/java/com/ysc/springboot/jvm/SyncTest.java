package com.ysc.springboot.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author yuanshancheng
 * @date 2021/4/17
 */
public class SyncTest {
    public static void main(String[] args) throws Exception {
//        noLock();
//        biaseInit();
//        biaseInit();
//        biase();
//        myHashCode();
//        myHashCode2();
//        lightWeight();
//        heightWeight();
        multi2();
    }

    static void multi2() throws InterruptedException {
        for (int i = 1; i <= 45; ++i) {
            System.out.println(i);
            A a = new A();
            new Thread(() -> {
                synchronized (a) {
                    int tmp = 1;
                }
            }).start();
            Thread.sleep(200);
            synchronized (a) {

                markWord(a);
            }
        }
        System.out.println("--------");

//        synchronized (tmpA) {
//            markWord(tmpA);
//        }
//
//        A tmpB = tmpA;
//        new Thread(() -> {
//            synchronized (tmpB) {
//                markWord(tmpB);
//            }
//        }).start();
//        A a = new A();
//        synchronized (a) {
//            //
//            markWord(a);
//        }
    }

    static void multi() throws InterruptedException {
        for (int i = 0; i < 42; ++i) {
            A a = new A();
            Thread thread = new Thread(() -> {
                markWord(a);
                synchronized (a) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread thread2 = new Thread(() -> {
                synchronized (a) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread2.start();
            Thread.sleep(200);
        }
    }

    // 当发送锁竞争时，直接变更为重量级锁
    static void heightWeight() throws InterruptedException {
        A a = new A();
        Thread thread1 = new Thread(() -> {
            synchronized (a) {
                markWord(a);
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (a) {
                markWord(a);
            }
        });
        thread1.start();
        thread2.start();
        // 等待所有线程执行完毕
        Thread.sleep(2000);
        // 重量级锁释放后变为无锁状态，其他字段均为0
        markWord(a);
    }

    // 偏向锁升级为轻量级锁，一旦发生锁竞争直接锁膨胀
    static void lightWeight() throws InterruptedException {
        A a = new A();
        new Thread(() -> {
            synchronized (a) {
                markWord(a);
            }
            System.out.println("lock released");
        }).start();

        Thread.sleep(2000);
        synchronized (a) {
            markWord(a);
        }
        Thread.sleep(1000);
        // 偏向锁释放后变为无锁，其他字段均为0
        markWord(a);

        Thread.sleep(2000);
        synchronized (a) {
            markWord(a);
        }
    }

    // 处于偏向锁状态下，计算hashcode，直接升级为重量级锁
    static void myHashCode2() {
        A a = new A();
        synchronized (a) {
            markWord(a);
            a.hashCode();
            markWord(a);
        }
    }

    // 计算hashcode之后直接加锁，直接添加轻量级锁
    // 若hashcode重写了，重写后的hashcode不存在在Mark Word中，对加锁无影响
    static void myHashCode() {
        A a = new A();
        a.hashCode();
        markWord(a);
        synchronized (a) {
            markWord(a);
        }
    }

    static void biase() throws InterruptedException {
        A a = new A();
        synchronized (a) {
            markWord(a);
        }
        System.out.println(Thread.currentThread().getId());
        Thread.sleep(2000);
        markWord(a);
    }

    // 偏向锁默认在jvm启动后4s开启，通过参数设置：-XX:BiasedLockingStartupDelay=0
    // 偏向锁，特殊的无锁状态，thread id和epoch均为0
    static void biaseInit() throws InterruptedException {
//        Thread.sleep(5000);
        Object o = new Object();
        markWord(o);
    }

    // 无锁
    static void noLock () {
        Object o = new Object();
        markWord(o);
    }

    static class A {
        boolean flag;
        Object o = new Object();
        boolean flag2;
//        @Override
//        public int hashCode() {
//            return flag ? 1 : 0;
//        }
    }

    static void markWord(Object o) {
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
