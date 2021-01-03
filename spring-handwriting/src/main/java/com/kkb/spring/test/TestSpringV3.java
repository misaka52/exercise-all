package com.kkb.spring.test;

import com.kkb.spring.factory.support.DefaultListableBeanFactory;
import com.kkb.spring.po.User;
import com.kkb.spring.reader.XmlBeanDefinitionReader;
import com.kkb.spring.resource.ClasspathResource;
import com.kkb.spring.resource.Resource;
import com.kkb.spring.service.UserService;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 版本3，模拟手写spring ioc容器，增加设计模式
 */
public class TestSpringV3 {

	@Test
	public void test() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 读取xml的beanDefinition阅读器
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		Resource resource = new ClasspathResource("beans.xml");
		InputStream inputStream = resource.getResource();

		reader.loadBeanDefinitions(inputStream);

		UserService userService = (UserService) beanFactory.getBean("userService");

		User user = new User();
		user.setUsername("zhangsan");
		List<User> users = userService.queryUsers(user);
		System.out.println("查询结果：" + users);
	}

}
