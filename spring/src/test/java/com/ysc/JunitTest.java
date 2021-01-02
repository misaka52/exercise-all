package com.ysc;

import com.ysc.configuration.MysqlConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yuanshancheng
 * @date 2020/12/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class JunitTest {
    @Autowired
    private MysqlConfiguration mysqlConfiguration;

    @Test
    public void test() {
        log.info("config:{}", mysqlConfiguration);
    }
}
