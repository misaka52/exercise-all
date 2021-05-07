package com.ysc.exercise.springcloud.consumer.client;

import com.ysc.exercise.springcloud.consumer.dto.Product;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yuanshancheng
 * @date 2021/5/7
 */
@Slf4j
@Component
public class ProducerTempBackFactory implements FallbackFactory<ProducerTempClient> {
    @Override
    public ProducerTempClient create(Throwable throwable) {
        return new ProducerTempClient() {
            @Override
            public String hello() {
                return null;
            }

            @Override
            public Product getById(Integer id) {
                log.warn("ProducerTempBackFactory降级");
                return null;
            }

            @Override
            public String sleep(int second) {
                return "ProducerTempBackFactory降级";
            }

            @Override
            public String error() {
                return "ProducerTempBackFactory降级";
            }
        };
    }
}
