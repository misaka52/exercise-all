package com.ysc;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author yuanshancheng
 * @date 2020/12/26
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Before(value = "execution(* com..TestController.hello())")
    public void before() {
        log.info("before-----");
    }

    @After(value = "execution(* com.*.*.TestController.hello(..))")
    public void after() {
        log.info("after-----");
    }

    // 定义切入点
    @Pointcut(value = "execution(* com.*.*.TestController.hello2(..))")
    public void point() {
    }

    // 复用切入点
    @Around(value = "com.ysc.LogAspect.point()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            log.info("around-before");
            Object[] args = joinPoint.getArgs();
            Object result = joinPoint.proceed(args);
            log.info("around-afterReturning");
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info("around-afterThrowing");
            return null;
        }
    }
}
