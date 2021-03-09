package com.ysc.exercise.springcloud.producer8081.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author yuanshancheng
 * @date 2021/1/25
 */
@Data
@Entity(name = "product")
public class Product {
    @Id
    private Integer id;
    private String name;
}
