package com.ysc.springboot.mysql;

import com.ysc.springboot.CommonResource;
import com.ysc.springboot.mapper.ProductMapper;
import com.ysc.springboot.service.ProductService;
import com.ysc.springboot.service.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/2/6
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LockTest {
    @Autowired
    private ProductService service;

    @Test
    public void test() throws InterruptedException {
        CommonResource.executorService.submit(new MyRunnable(Arrays.asList(1, 2)));
        CommonResource.executorService.submit(new MyRunnable(Arrays.asList(2, 1)));
        Thread.sleep(10000000);
    }

    class MyRunnable implements Runnable {

        public MyRunnable(List<Integer> ids) {
            this.ids = ids;
        }
        private List<Integer> ids;

        @Override
        public void run() {
            try {
                service.selectInLock(ids);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("over");
        }
    }
}
