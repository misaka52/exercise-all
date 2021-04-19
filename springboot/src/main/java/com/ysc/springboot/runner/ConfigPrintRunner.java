package com.ysc.springboot.runner;

import com.ysc.springboot.IConfig;
import com.ysc.springboot.config.CommonConfig;
import com.ysc.springboot.config.EnvConfig;
import com.ysc.springboot.config.PropConfig;
import com.ysc.springboot.service.ProductService;
import com.ysc.springboot.service.ServiceA;
import com.ysc.springboot.service.ServiceB;
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
    private IConfig iConfig;
    @Autowired
    private EnvConfig envConfig;
    @Autowired
    private PropConfig propConfig;
    @Autowired
    private ProductService productService2;
//    @Autowired
//    private ProductServiceImpl productServiceImpl;
    @Autowired
    private ServiceA serviceA;
    @Autowired
    private ServiceB serviceB;
//    @Autowired
//    private WrapService wrapService;

    @Override
    public void run(String... args) throws Exception {
        log.info("commonConfig:{}", commonConfig);
        log.info("envConfig:{}", envConfig.getEnv());
        log.info("propConfig:{}", propConfig);
        log.info("serviceA:{}", serviceA.getServiceB().hello());
        log.info("serviceB:{}", serviceB.getServiceA().hello());
//        log.info("wrapService:{}", wrapService.wrap("test"));
    }
}