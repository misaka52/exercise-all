package com.ysc.springboot.runner;

import com.ysc.springboot.config.CommonConfig;
import com.ysc.springboot.config.EnvConfig;
import com.ysc.springboot.config.PropConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * class description:
 *
 * @author yuanshancheng
 * @date 2020/12/31
 */
@Component
@Slf4j
public class ConfigPrintRunner implements CommandLineRunner {
    @Autowired
    private CommonConfig commonConfig;
    @Autowired
    private EnvConfig envConfig;
    @Autowired
    private PropConfig propConfig;

    @Override
    public void run(String... args) throws Exception {
        log.info("commonConfig:{}", commonConfig);
        log.info("envConfig:{}", envConfig.getEnv());
        log.info("propConfig:{}", propConfig);
    }
}