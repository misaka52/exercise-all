package com.kkb.spring.factory.support;

import com.kkb.spring.ioc.BeanDefinition;
import com.kkb.spring.registry.BeanDefinitionRegistry;

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
		return null;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		this.beanDefinitions.put(beanName, beanDefinition);
	}

}
