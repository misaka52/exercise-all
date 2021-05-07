package com.ysc.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanshancheng
 * @date 2021/5/6
 */
@RestController
@RequestMapping("redis")
@RequiredArgsConstructor
public class RedisController {
    private final StringRedisTemplate redisTemplate;

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
