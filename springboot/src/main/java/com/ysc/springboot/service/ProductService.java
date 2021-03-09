package com.ysc.springboot.service;

import com.ysc.springboot.pojo.Product;

import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/1/6
 */
public interface ProductService {
    public List<Product> selectAll();
    int insertDouble();
    void selectInLock(List<Integer> ids) throws InterruptedException;
}
