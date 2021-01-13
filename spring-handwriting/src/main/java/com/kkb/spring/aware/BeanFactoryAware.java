package com.kkb.spring.aware;

import com.kkb.spring.factory.BeanFactory;

/**
 * @author yuanshancheng
 * @date 2021/1/12
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
