package com.ysc.exercise.springcloud.consumer.client;

import com.ysc.exercise.springcloud.consumer.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanshancheng
 * @date 2021/1/26
 */
@Component
@RequestMapping("fallback/temp")
@Slf4j
public class ProducerTempClientHystrix implements ProducerTempClient {
    @Override
    public String hello() {
        return null;
    }

    @Override
    public Product getById(Integer id) {
        log.warn("降级");
        return null;
    }

    @Override
    public String sleep(int second) {
        return "sleep()被降级";
    }

    @Override
    public String error() {
        return "error()降级";
    }
}
