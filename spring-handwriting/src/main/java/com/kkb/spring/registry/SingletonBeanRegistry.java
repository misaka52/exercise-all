package com.kkb.spring.registry;

public interface SingletonBeanRegistry {

	Object getSingleton(String beanName);

	void addSingleton(String beanName, Object bean);
}
