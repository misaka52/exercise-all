package com.ysc.springboot.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author yuanshancheng
 * @date 2021/1/21
 */
public class RedissonTest {
    private static Config config = new Config();
    private static Redisson redisson;
    static {
        String[] port = {"8001", "8002", "8003", "8004", "8005", "8006"};
        String[] urls = new String[port.length];
        for (int i = 0; i < urls.length; ++i) {
            urls[i] = "127.0.0.1:" + port[i];
        }
        config.useClusterServers()
                .setScanInterval(2000)
                .addNodeAddress(urls);
        redisson = (Redisson) Redisson.create(config);
    }

    public static Redisson getRedisson() {
        return redisson;
    }


    public static void main(String[] args) {
        Redisson redisson = getRedisson();
        String key = "redisson";
        RLock lock = redisson.getLock(key);

        lock.lock(1, TimeUnit.SECONDS);

        lock.unlock();
        System.out.println("释放成功");
    }

    public static void lock() {

    }


}
