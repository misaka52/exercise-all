package com.ysc.exercise.springcloud.producer8081.repository;

import com.ysc.exercise.springcloud.producer8081.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuanshancheng
 * @date 2021/1/25
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
