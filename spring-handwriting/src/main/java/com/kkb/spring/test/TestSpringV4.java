package com.kkb.spring.test;

import com.kkb.spring.po.User;
import com.kkb.spring.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * 使用spring容器获取bean
 * @author yuanshancheng
 * @date 2021/1/3
 */
public class TestSpringV4 {
    private static final String USER_SERVICE = "userService";
    /**
     * 入口1，最初spring提供一个bean工厂，XMLBeanFactory，后来被遗弃了。不常用
     */
    @Test
    public void test1() {
        String path = "spring/beans.xml";
        Resource resource = new ClassPathResource(path);
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);

        UserService userService = (UserService) xmlBeanFactory.getBean(USER_SERVICE);
        dbQuery(userService);
    }

    private void dbQuery(UserService userService) {
        User user = new User();
        user.setUsername("zhangsan");
        List<User> users = userService.queryUsers(user);
        System.out.println("查询结果：" + users);
    }

    /**
     * 入口2，方法3就是按照这套逻辑简单实现的
     */
    @Test
    public void test2() {
        String path = "spring/beans.xml";
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(path);

        UserService userService = (UserService) beanFactory.getBean(USER_SERVICE);
        dbQuery(userService);
    }
}
