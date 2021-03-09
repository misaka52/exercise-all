package com.ysc.springboot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author yuanshancheng
 * @date 2021/1/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void redis() {
        String keyPrefix = "key";
        String valuePrefix = "value";
        for (int i = 0; i < 100; ++i) {
            String key = keyPrefix + i;
            String value = valuePrefix + i;
            redisTemplate.opsForValue().set(key, value);
            System.out.println(redisTemplate.opsForValue().get(key));
        }
    }
}
