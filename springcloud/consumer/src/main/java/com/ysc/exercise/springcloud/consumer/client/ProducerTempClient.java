package com.ysc.exercise.springcloud.consumer.client;

import com.ysc.exercise.springcloud.consumer.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanshancheng
 * @date 2021/1/25
 */
@FeignClient(value = "producer-8081", fallback = ProducerTempClientHystrix.class)
@RequestMapping("temp")
public interface ProducerTempClient {
    @GetMapping("hello")
    String hello();

    @GetMapping("product/get/{id}")
    Product getById(@PathVariable Integer id);

    @GetMapping("sleep/{second}")
    String sleep(@PathVariable int second);

    @GetMapping("error")
    String error();
}
