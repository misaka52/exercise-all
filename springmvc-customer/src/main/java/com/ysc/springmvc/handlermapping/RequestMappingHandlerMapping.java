package com.ysc.springmvc.handlermapping;

import com.kkb.spring.aware.BeanFactoryAware;
import com.kkb.spring.factory.BeanFactory;
import com.kkb.spring.factory.support.DefaultListableBeanFactory;
import com.kkb.spring.ioc.BeanDefinition;
import com.ysc.springmvc.annotation.Controller;
import com.ysc.springmvc.annotation.RequestMapping;
import com.ysc.springmvc.consts.ConstConfig;
import com.ysc.springmvc.handler.HandlerMethod;
import com.ysc.springmvc.handlermapping.inter.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanshancheng
 * @date 2021/1/12
 */
public class RequestMappingHandlerMapping implements HandlerMapping, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    private Map<String, Object> cache = new HashMap<>();

    private void init() {
        List<BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String clazzName = beanDefinition.getClazzName();
            Class<?> clazz = resolveClass(clazzName);
            Controller controller = clazz.getAnnotation(Controller.class);
            if (controller != null) {
                Method[] declaredMethods = clazz.getDeclaredMethods();
                RequestMapping clazzRequestMapping = clazz.getAnnotation(RequestMapping.class);
                String clazzUri = clazzRequestMapping != null ? clazzRequestMapping.value() : "";
                if (clazzUri.length() > 0 && !clazzUri.startsWith("/")) {
                    clazzUri = "/" + clazzUri;
                }
                Object controllerBean = beanFactory.getBean(beanDefinition.getBeanName());
                for (Method method : declaredMethods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                        String methodUri = methodRequestMapping.value();

                        StringBuilder uri = new StringBuilder(ConstConfig.projectUrlPrefix);
                        uri.append(clazzUri);
                        if (methodUri.length() > 0 && !methodUri.startsWith("/")) {
                            uri.append("/");
                        }
                        uri.append(methodRequestMapping.value());

                        cache.put(uri.toString(),
                                new HandlerMethod(controllerBean, method));
                    }
                }
            }
        }
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        return cache.get(request.getRequestURI());
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
