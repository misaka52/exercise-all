package com.kkb.spring.factory;

/**
 * spring容器工厂的老大
 * 
 * @author 灭霸詹
 *
 */
public interface BeanFactory {
	Object getBean(String beanName);
}
