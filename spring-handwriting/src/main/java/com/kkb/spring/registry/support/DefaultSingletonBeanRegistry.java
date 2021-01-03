package com.kkb.spring.registry.support;

import com.kkb.spring.registry.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
	// K:BeanName
	// V:Bean实例对象
	private Map<String, Object> singletonObjects = new HashMap<String, Object>();

	@Override
	public Object getSingleton(String beanName) {
		return this.singletonObjects.get(beanName);
	}

	@Override
	public void addSingleton(String beanName, Object bean) {
		this.singletonObjects.put(beanName, bean);
	}

}
