package com.ysc.exercise.springcloud.producer8081.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ysc.exercise.springcloud.producer8081.pojo.Product;
import com.ysc.exercise.springcloud.producer8081.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/1/25
 */
@RestController
@RequestMapping("temp")
@Slf4j
public class TempController {
    @Autowired
    private ProductService productService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("hello")
    public String hello() {
        return "well";
    }

    @HystrixCommand(fallbackMethod = "getHystrix")
    @GetMapping("hystrix")
    public String hystrix() {
        return "well";
    }


    @PostMapping("product/getAll")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("discovery")
    public Object discovery() {
        // 只能获取到consumer？？
        List<String> services = discoveryClient.getServices();
        for (String name :services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(name);
            for (ServiceInstance instance : instances) {
                log.info("serviceId:{}, uri:{}, host:{}, port:{}", instance.getServiceId(),
                        instance.getUri(), instance.getHost(), instance.getPort());
            }
        }
        return services;
    }

    @GetMapping("product/get/{id}")
    public Product getById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @GetMapping("sleep/{second}")
    public String sleep(@PathVariable int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sleep " + second + "s success";
    }

    @HystrixCommand(fallbackMethod = "getHystrix")
    @GetMapping("error")
    public String error() {
        throw new RuntimeException("自主抛出异常");
    }

    public String getHystrix() {
        return "服务被降级";
    }


}
