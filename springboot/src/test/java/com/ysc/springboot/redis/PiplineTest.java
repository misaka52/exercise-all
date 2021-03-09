package com.ysc.springboot.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author yuanshancheng
 * @date 2021/1/20
 */
public class PiplineTest {
    public static void main(String[] args) {
        multi();
        pipline();
    }

    /**
     * 管道方式执行，一次执行批量命令
     */
    static void pipline() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i) {
            pipeline.set("key" + i, "value" + i);
            pipeline.del("key" + i);
        }
        pipeline.sync();
        System.out.println(System.currentTimeMillis() - start);
    }

    static void multi() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i) {
            jedis.set("key" + i, "value" + i);
            jedis.del("key" + i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
