package com.kkb.spring.service;

import java.util.List;

import com.kkb.spring.dao.UserDao;
import com.kkb.spring.po.User;

public class UserServiceImpl implements UserService {

	public void init() {
		System.out.println("UserServiceImpl 初始化");
	}

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> queryUsers(User user) {
		return userDao.queryUserList("queryUserById",user);
	}

}
