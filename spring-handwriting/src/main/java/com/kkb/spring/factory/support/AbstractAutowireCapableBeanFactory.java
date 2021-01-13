package com.kkb.spring.factory.support;

import com.kkb.spring.aware.Aware;
import com.kkb.spring.aware.BeanFactoryAware;
import com.kkb.spring.factory.BeanFactory;
import com.kkb.spring.ioc.BeanDefinition;
import com.kkb.spring.ioc.PropertyValue;
import com.kkb.spring.ioc.RuntimeBeanReference;
import com.kkb.spring.ioc.TypedStringValue;
import com.kkb.spring.utils.ReflectUtils;

import java.util.List;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	public Object createBean(BeanDefinition beanDefinition) {
		String clazzName = beanDefinition.getClazzName();
		if (clazzName == null) {
			return null;
		}
		// TODO 第一步：new对象（实例化）
		Object bean = createInstanceBean(clazzName);
		if (bean == null) {
			return null;
		}
		// TODO 第二步：依赖注入
		populateBean(bean, beanDefinition);
		// TODO
		// 第三步：初始化，就是调用initMethod指定的初始化方法，或者实现了InitializingBean接口的afterPropertiesSet方法----
		initMethod(bean, beanDefinition);
		return bean;
	}

	private void initMethod(Object bean, BeanDefinition beanDefinition) {
		// TODO 判断Bean是否是实现了Aware接口
		if (bean instanceof Aware) {
			if (bean instanceof BeanFactoryAware) {
				((BeanFactoryAware) bean).setBeanFactory(this);
			}
		}
		// TODO 判断是否实现了InitilizingBean接口，如果实现，则直接调用该bean的afterPropertiesSet方法去初始化
		// 调用通过bean标签指定的初始化方法，比如通过init-method标签属性指定的方法
		String initMethod = beanDefinition.getInitMethod();
		if (initMethod == null) {
			return;
		}
		ReflectUtils.invokeMethod(bean, initMethod);
	}

	private void populateBean(Object bean, BeanDefinition beanDefinition) {
		List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
		if (propertyValues != null && propertyValues.size() > 0) {
			for (PropertyValue pv : propertyValues) {
				String name = pv.getName();
				Object value = pv.getValue();

				Object valueToUse = null;
				if (value instanceof TypedStringValue) {
					TypedStringValue typedStringValue = (TypedStringValue) value;
					String stringValue = typedStringValue.getValue();
					Class<?> targetType = typedStringValue.getTargetType();

					// TODO 通过策略模式去优化
					// typedStringValue.getTypeHandler();
					if (targetType == Integer.class) {
						valueToUse = Integer.parseInt(stringValue);
					} else if (targetType == String.class) {
						valueToUse = stringValue;
					}
				} else if (value instanceof RuntimeBeanReference) {
					RuntimeBeanReference beanReference = (RuntimeBeanReference) value;
					String ref = beanReference.getRef();
					valueToUse = getBean(ref);
				}

				// 通过反射去给bean实例去设置指定name的值
				ReflectUtils.setProperty(bean, name, valueToUse);
			}
		}

	}

	private Object createInstanceBean(String clazzName) {
		// TODO 通过实例工厂方式去创建Bean实例，比如通过factory-bean标签属性指的FactoryBean工厂去创建实例
		// TODO 通过静态工厂方法方式去创建Bean实例，比如通过factory-method标签属性指的静态工厂方法去创建实例

		// 构造方法去创建Bean实例（此处我们只针对无参构造进行操作）
		Object bean = ReflectUtils.createObject(clazzName);

		return bean;
	}

}
