package com.ysc.exercise.springcloud.producer8081.service;

import com.ysc.exercise.springcloud.producer8081.pojo.Product;

import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/1/25
 */
public interface ProductService {
    List<Product> findAll();
    Product findById(Integer id);
}
