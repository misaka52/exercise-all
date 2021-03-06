package com.ysc.springboot.controller;

import com.ysc.springboot.pojo.Product;
import com.ysc.springboot.service.ProductService;
import com.ysc.springboot.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * class description:
 *
 * @author yuanshancheng
 * @date 2020/12/31
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private ProductService productServiceImpl;
    
    @GetMapping("hello")
    public String hello(String name) {
        log.debug("进入hello方法");
        log.info("你好,{}", name);
        log.debug("退出hello方法");
        return "hello";
    }
    
    @GetMapping("ex")
    public String ex() {
        throw new RuntimeException("运行异常");
    }

    @GetMapping("sleep/{time}")
    public String sleep(@PathVariable int time) throws InterruptedException {
        Thread.sleep(time * 1000);
        return "success";
    }

    @GetMapping("product/all")
    public List<Product> getAll() {
        return productServiceImpl.selectAll();
    }

    @GetMapping("product/tx")
    public String productTx() {
        return String.valueOf(productServiceImpl.insertDouble());
    }
}