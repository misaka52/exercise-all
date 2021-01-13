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

    /**
     * 批量插入，多条语句用分号分割。仅当数据连接添加allowMultiQueries=true才支持
     * @param products
     * @return
     */
    int insertMuti(List<Product> products);
}