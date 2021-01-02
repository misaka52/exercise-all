package com.ysc.rest;

import com.ysc.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanshancheng
 * @date 2020/12/26
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("hello")
    public String hello() {
        log.info("调用hello()");
        return "success";
    }

    @GetMapping("hello2")
    public String hello2() {
        log.info("调用hello2()");
        return "success";
    }

    @GetMapping("sleep")
    public String sleep() throws InterruptedException {
        Thread.sleep(500);
        testService.fun();
        return String.valueOf(System.currentTimeMillis());
    }
}
