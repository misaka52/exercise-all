package com.ysc.springboot.juc.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author yuanshancheng
 * @date 2021/3/7
 */
public class StampedLockTest {
    private static StampedLock stampedLock = new StampedLock();
    private int x;
    private int y;

    public void move(int x, int y) {
        long stamp = stampedLock.writeLock();
        try {
            this.x = x;
            this.y = y;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public int get() {
        // 记录版本号
        long stamp = stampedLock.tryOptimisticRead();
        int currentX = x;
        int currentY = y;
        if (!stampedLock.validate(stamp)) {
            // 校验不通过尝试获取读锁
            long tmp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(tmp);
            }
        }
        return currentX * currentY;
    }

    // 读锁升级为写锁
    public void moveIfAtOrigin(int newX, int newY) {
        long stamp = stampedLock.readLock();
        try {
            while (x == 0 && y == 0) {
                long stamp2 = stampedLock.tryConvertToWriteLock(stamp);
                if (stamp2 != 0) {
                    // 转换成功
                    x = newX;
                    y = newY;
                    break;
                } else {
                    stampedLock.unlockRead(stamp);
                    stamp = stampedLock.writeLock();
                }
            }
        } finally {
            stampedLock.unlock(stamp);
        }
    }
}
