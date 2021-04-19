package com.ysc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;

/**
 * @author yuanshancheng
 * @date 2021/4/17
 */
@RestController
@RequestMapping("limit")
public class CurrentLimitController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/v1")
    public String limit() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/limit.lua")));
        script.setResultType(Long.class);
        Long res = redisTemplate.execute(script, Collections.singletonList("cur"), "10", "100");
        return String.valueOf(res);
    }

    @GetMapping("/sleep")
    public String sleep(@RequestParam(required = false, defaultValue = "0") Integer s) throws InterruptedException {
        Thread.sleep(s * 1000);
        return String.valueOf(s);
    }

    @GetMapping("/v2")
    public String limit2() throws IOException {
        return redisTemplate.opsForValue().get("cur");
    }

    @GetMapping("/v3")
    public String limit3() throws IOException {
        DefaultRedisScript<Integer> script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/t2.lua")));
        script.setResultType(Integer.class);
        Integer res = redisTemplate.execute(script,
                Collections.singletonList("cur"), "1");
        return String.valueOf(res);
    }

    @GetMapping("/v4")
    public String limit4() throws IOException {
        String str = "if redis.call('get', KEYS[1]) == ARGV[1] then return 1 else return 0 end";
        DefaultRedisScript<Long> script = new DefaultRedisScript<>(str, Long.class);
        Long res = redisTemplate.execute(script,
                Collections.singletonList("cur"), "1");
        return String.valueOf(res);
    }
}
