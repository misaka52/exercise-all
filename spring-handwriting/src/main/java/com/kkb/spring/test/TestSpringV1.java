package com.kkb.spring.test;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import com.kkb.spring.dao.UserDaoImpl;
import com.kkb.spring.po.User;
import com.kkb.spring.service.UserServiceImpl;

/**
 * 最初手写版本，全部手动注入bean
 */
public class TestSpringV1 {

	@Test
	public void test() throws Exception {
		// 根据用户名称查询用户信息
		UserServiceImpl userService = new UserServiceImpl();
		UserDaoImpl userDao = new UserDaoImpl();
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		userDao.setDataSource(dataSource);
		userService.setUserDao(userDao);

		User user = new User();
		user.setUsername("zhangsan");
		List<User> users = userService.queryUsers(user);
		System.out.println("查询结果：" + users);

	}

	@Test
	public void reflect() {
		Class<?> clazz = User.class;
		System.out.println("----getFields----");
		// 返回public变量
		print(clazz.getFields());
		System.out.println("----getDeclaredFields----");
		print(clazz.getDeclaredFields());
	}

	private void print(Field[] fields) {
		for (Field field : fields) {
			System.out.println(field);
		}
	}

}
