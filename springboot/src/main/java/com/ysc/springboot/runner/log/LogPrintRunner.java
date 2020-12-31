package com.ysc.springboot.runner.log;

import lombok.extern.slf4j.Slf4j;
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
public class LogPrintRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.trace("trace日志");
        log.debug("debug日志");
        log.info("info日志");
        log.warn("warn日志");
        log.error("error日志");
    }
}