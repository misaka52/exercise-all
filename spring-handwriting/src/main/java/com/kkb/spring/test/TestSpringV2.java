package com.kkb.spring.test;

import com.kkb.spring.ioc.BeanDefinition;
import com.kkb.spring.ioc.PropertyValue;
import com.kkb.spring.ioc.RuntimeBeanReference;
import com.kkb.spring.ioc.TypedStringValue;
import com.kkb.spring.po.User;
import com.kkb.spring.service.UserService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版本2，模拟手写自动注入流程
 */
public class TestSpringV2 {

	/**
	 * bean缓存
	 */
	private Map<String, Object> beanCache = new HashMap<>();
	/**
	 * BeanDefinition缓存
	 */
	private Map<String, BeanDefinition> beanDefinitionCache = new HashMap<>();

	@Before
	public void init() {
		String location = "beans.xml";
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
		Document document = createDocument(inputStream);
		registerBeanDefinitions(document.getRootElement());
	}

	@Test
	public void test() throws Exception {
		User user = new User();
		user.setUsername("zhangsan");
		UserService userService = (UserService) getBean("userService");
		List<User> users = userService.queryUsers(user);
		System.out.println("查询结果：" + users);
	}

	public Object getBean(String name) {
		Object bean = beanCache.get(name);
		if (bean != null) {
			return bean;
		}

		BeanDefinition beanDefinition = beanDefinitionCache.get(name);
		if (beanDefinition == null || beanDefinition.getClazzName() == null) {
			System.out.println("找不到beanDefinition");
			return null;
		}

		if (beanDefinition.isSingleton()) {
			bean = createBean(beanDefinition);
			beanCache.put(name, bean);
		} else if (beanDefinition.isPrototype()) {
			bean = createBean(beanDefinition);
		} else {
			System.out.println("bean类型错误");
		}
		return bean;
	}

	private Document createDocument(InputStream inputStream) {
		Document document = null;
		try {
			SAXReader reader = new SAXReader();
			document = reader.read(inputStream);
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析文档
	 */
	private void registerBeanDefinitions(Element rootElement) {
		List<Element> list = rootElement.elements();
		for (Element element : list) {
			String name = element.getName();
			if ("bean".equals(name)) {
				// bean方式解析
				parseDefaultElement(element);
			} else {
				// 解析其他特殊标签
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void parseDefaultElement(Element beanElement) {
		try {
			if (beanElement == null) {
				return;
			}
			// 获取id属性
			String id = beanElement.attributeValue("id");

			// 获取name属性
			String name = beanElement.attributeValue("name");
			// 获取class属性
			String clazzName = beanElement.attributeValue("class");
			if (clazzName == null || "".equals(clazzName)) {
				return;
			}

			// 获取init-method属性
			String initMethod = beanElement.attributeValue("init-method");
			// 获取scope属性
			String scope = beanElement.attributeValue("scope");
			scope = scope != null && !scope.equals("") ? scope : "singleton";

			String beanName = id == null ? name : id;
			Class<?> clazzType = Class.forName(clazzName);
			beanName = beanName == null ? clazzType.getSimpleName() : beanName;
			// 创建BeanDefinition对象
			// 此次可以使用构建者模式进行优化
			BeanDefinition beanDefinition = new BeanDefinition(clazzName, beanName);
			beanDefinition.setInitMethod(initMethod);
			beanDefinition.setScope(scope);
			// 获取property子标签集合
			List<Element> propertyElements = beanElement.elements();
			for (Element propertyElement : propertyElements) {
				parsePropertyElement(beanDefinition, propertyElement);
			}

			// 注册BeanDefinition信息
			this.beanDefinitionCache.put(beanName, beanDefinition);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void parsePropertyElement(BeanDefinition beanDefination, Element propertyElement) {
		if (propertyElement == null) {
			return;
		}

		// 获取name属性
		String name = propertyElement.attributeValue("name");
		// 获取value属性
		String value = propertyElement.attributeValue("value");
		// 获取ref属性
		String ref = propertyElement.attributeValue("ref");

		// 如果value和ref都有值，则返回
		if (value != null && !value.equals("") && ref != null && !ref.equals("")) {
			return;
		}

		/**
		 * PropertyValue就封装着一个property标签的信息
		 */
		PropertyValue pv = null;

		if (value != null && !value.equals("")) {
			// 因为spring配置文件中的value是String类型，而对象中的属性值是各种各样的，所以需要存储类型
			TypedStringValue typeStringValue = new TypedStringValue(value);

			Class<?> targetType = getTypeByFieldName(beanDefination.getClazzName(), name);
			typeStringValue.setTargetType(targetType);

			pv = new PropertyValue(name, typeStringValue);
			beanDefination.addPropertyValue(pv);
		} else if (ref != null && !ref.equals("")) {

			RuntimeBeanReference reference = new RuntimeBeanReference(ref);
			pv = new PropertyValue(name, reference);
			beanDefination.addPropertyValue(pv);
		} else {
			return;
		}
	}

	private Class<?> getTypeByFieldName(String clazzName, String fieldName) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(clazzName);
			Field field = clazz.getDeclaredField(fieldName);
			return field.getType();
		} catch (ClassNotFoundException | NoSuchFieldException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 创建bean，分为三步
	 * 	1、生成对象。一般通过反射方式实现
	 * 	2、依赖注入。
	 * 	3、bean初始化，调用初始化方法
	 * @param beanDefinition
	 * @return
	 */
	private Object createBean(BeanDefinition beanDefinition) {
		// 1、生成对象
		Object bean = generateBean(beanDefinition);
		if (bean == null) {
			return null;
		}
		// 2、依赖注入
		dependencyInject(bean, beanDefinition);

		// 3、对象初始化，调用初始化方法
		beanInit(bean, beanDefinition);
		return bean;
	}

	private void beanInit(Object bean, BeanDefinition beanDefinition) {
		// todo 1、判断bean是否实现了Aware接口
		// todo 2、判断bean是否实现了InitializingBean接口，如果实现则直接调用bean的afterPropertiesSet方法去初始化
		// todo 3、调用标签属性中定义的init-method
		if (beanDefinition.getInitMethod() != null) {
			invokeMethod(bean, beanDefinition.getInitMethod());
		}
	}












	private void invokeMethod(Object bean, String initMethod) {
		Class<?> clazz = bean.getClass();
		try {
			Method method = clazz.getDeclaredMethod(initMethod);
			method.setAccessible(true);

			method.invoke(bean);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	private void dependencyInject(Object bean, BeanDefinition beanDefinition) {
		List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();
		if (propertyValueList == null || propertyValueList.size() <= 0) {
			return;
		}
		for (PropertyValue pv : propertyValueList) {
			String name = pv.getName();
			Object value = pv.getValue();

			Object targetValue = null;
			if (value instanceof TypedStringValue) {
				TypedStringValue realValue = (TypedStringValue) value;

				Class<?> clazz = realValue.getTargetType();
				String strValue = realValue.getValue();

				// todo 可扩展
				if (clazz == Integer.class) {
					targetValue = Integer.valueOf(strValue);
				} else if (clazz == String.class) {
					targetValue = strValue;
				}
				// 设置属性值
			} else if (value instanceof RuntimeBeanReference) {
				RuntimeBeanReference runtimeBeanReference = (RuntimeBeanReference) value;
				targetValue = getBean(runtimeBeanReference.getRef());
			} else {
				System.out.println("value类型错误");
			}
			setProperty(bean, name, targetValue);
		}
	}

	private void setProperty(Object bean, String name, Object value) {
		Class<?> clazz = bean.getClass();
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);

			field.set(bean, value);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private Object generateBean(BeanDefinition beanDefinition) {
		// 1、todo 通过实例工厂创建bean，比如通过factory-bean标签属性指定FactoryBean实例工厂去创建
		// 2、todo 通过静态工厂方法创建bean，比如通过factory-method标签属性指定静态工厂方法
		// 3、通过反射生成
		if (beanDefinition.getClazzName() == null) {
			return null;
		}
		String clazzName = beanDefinition.getClazzName();
		return newInstance(clazzName);
	}

	private Object newInstance(String clazzName) {
		try {
			Class<?> clazz = Class.forName(clazzName);
			return newInstance(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Object newInstance(Class<?> clazz) {
		try {
			Constructor constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);

			return constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
