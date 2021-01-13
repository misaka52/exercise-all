package com.kkb.spring.factory.support;

import com.kkb.spring.ioc.BeanDefinition;
import com.kkb.spring.registry.BeanDefinitionRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring中真正的BeanFactory实例
 * 
 * @author 灭霸詹
 *
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
	// K:BeanName
	// V:BeanDefinition对象
	private Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

	@Override
	public BeanDefinition getBeanDefinition(String beanName) {
		return this.beanDefinitions.get(beanName);
	}

	@Override
	public List<BeanDefinition> getBeanDefinitions() {
		List<BeanDefinition> beanDefinitionList = new ArrayList<>();
		for (BeanDefinition beanDefinition : beanDefinitions.values()) {
			beanDefinitionList.add(beanDefinition);
		}
		return beanDefinitionList;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		this.beanDefinitions.put(beanName, beanDefinition);
	}

	@Override
	public <T> List<T> getBeansByType(Class<T> clazz) {
		List<T> results = new ArrayList<>();
		for (BeanDefinition beanDefinition : beanDefinitions.values()) {
			String className = beanDefinition.getClazzName();
			Class<?> type = resolveClass(className);
			// 判断调用者类是否为参数类的父类
			if (clazz.isAssignableFrom(type)) {
				Object bean = getBean(beanDefinition.getBeanName());
				results.add((T) bean);
			}
		}
		return results;
	}

	public Class<?> resolveClass(String clazzName) {
		try {
			return Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
