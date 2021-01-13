package com.ysc.springboot;

import com.ysc.springboot.mapper.ProductMapper;
import com.ysc.springboot.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringmvcApplicationTests {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void commonTest() {
        insertMutl();
    }

    private void insertMutl() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(6, "aaa"));
        productList.add(new Product(7, "bbb"));
        productList.add(new Product(8, "idDuilicate"));

        productMapper.insertMuti(productList);
    }
}
