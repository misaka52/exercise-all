package com.ysc.exercise.zuulproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ZuulProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulProducerApplication.class, args);
    }

}
