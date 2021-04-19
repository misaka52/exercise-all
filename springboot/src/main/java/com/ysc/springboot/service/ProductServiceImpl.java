package com.ysc.springboot.service;

import com.ysc.springboot.mapper.ProductMapper;
import com.ysc.springboot.pojo.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * class description:
 *
 * @author yuanshancheng
 * @date 2020/12/31
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;

    @Override
    public List<Product> selectAll() {
        return mapper.selectAll();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int insertDouble() {
        Product product = new Product();
        product.setId(ThreadLocalRandom.current().nextInt());
        product.setName("zhangsan");
        mapper.insert(product);
        log.info("成功插入一条,id={}", product.getId());
        mapper.insert(product);

        return 2;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void selectInLock(List<Integer> ids) throws InterruptedException {
        for (Integer id : ids) {
            mapper.selectInLock(id);
            Thread.sleep(3000);
        }
        System.out.println("任务完成" + ids);
    }
}