package com.ysc.exercise.springcloud.consumer.runner;

import com.alibaba.fastjson.JSON;
import com.ysc.exercise.springcloud.consumer.client.ProducerTempClient;
import com.ysc.exercise.springcloud.consumer.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/1/25
 */
@Component
@Slf4j
public class TestRunner implements CommandLineRunner {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProducerTempClient producerTempClient;

    @Override
    public void run(String... args) throws Exception {
//        producerRest();
        producerFeign();
    }

    private void producerRest() {
        String baseUrl = "http://producer-8081/temp/";
        String value = restTemplate.getForEntity(baseUrl + "hello", String.class).getBody();
        System.out.println("response result:" + value);

        List<Product> list = (List<Product>) restTemplate.postForEntity(baseUrl + "product/getAll", null, List.class)
                .getBody();
        System.out.println(JSON.toJSONString(list));
    }

    private void producerFeign() {
//        log.info("hello():{}", producerTempClient.hello());
//        log.info("getById(1):{}", producerTempClient.getById(1));
//        log.info("sleep(1):{}", producerTempClient.sleep(1));
        // 读超时不会触发降级方法回调
//        log.info("sleep(3):{}", producerTempClient.sleep(3));
        log.info("error():{}", producerTempClient.error());
    }
}
