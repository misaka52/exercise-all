package com.ysc.exercise.springcloud.producer8081.service.impl;

import com.ysc.exercise.springcloud.producer8081.pojo.Product;
import com.ysc.exercise.springcloud.producer8081.repository.ProductRepository;
import com.ysc.exercise.springcloud.producer8081.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.cmm.Profile;

import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/1/25
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;
    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
