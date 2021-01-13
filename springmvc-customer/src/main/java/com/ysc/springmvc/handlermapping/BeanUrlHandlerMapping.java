package com.ysc.springmvc.handlermapping;

import com.kkb.spring.aware.BeanFactoryAware;
import com.kkb.spring.factory.BeanFactory;
import com.kkb.spring.factory.support.DefaultListableBeanFactory;
import com.kkb.spring.ioc.BeanDefinition;
import com.ysc.springmvc.consts.ConstConfig;
import com.ysc.springmvc.handler.QueryGradeHandler;
import com.ysc.springmvc.handler.QueryUserHandler;
import com.ysc.springmvc.handlermapping.inter.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ysc.springmvc.consts.ConstConfig.projectUrlPrefix;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public class BeanUrlHandlerMapping implements HandlerMapping, BeanFactoryAware {
    private Map<String, Object> cache = new HashMap<>();
    private DefaultListableBeanFactory beanFactory;

    private void init() {
        List<BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String name = beanDefinition.getBeanName();
            if (name.startsWith("/")) {
                Object bean = beanFactory.getBean(name);
                cache.put(projectUrlPrefix + name, bean);
            }
        }
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        return cache.get(request.getRequestURI());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
