package com.ysc.springboot.mapper;

import com.ysc.springboot.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * class description:
 *
 * @author yuanshancheng
 * @date 2020/12/31
 */
@Mapper
public interface ProductMapper {
    List<Product> selectAll();

    int insert(Product product);
}