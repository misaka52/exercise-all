package com.kkb.spring.registry;

import com.kkb.spring.ioc.BeanDefinition;

import java.util.List;

/**
 * 专门用来对存储的BeanDefinition集合进行操作的功能接口
 * 
 * @author 灭霸詹
 *
 */
public interface BeanDefinitionRegistry {

	BeanDefinition getBeanDefinition(String beanName);

	List<BeanDefinition> getBeanDefinitions();

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

	<T> List<T> getBeansByType(Class<T> clazz);
}
