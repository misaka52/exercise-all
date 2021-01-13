package com.ysc.springmvc.servlet;

import com.kkb.spring.factory.BeanFactory;
import com.kkb.spring.factory.support.DefaultListableBeanFactory;
import com.kkb.spring.reader.XmlBeanDefinitionReader;
import com.kkb.spring.registry.BeanDefinitionRegistry;
import com.kkb.spring.resource.ClasspathResource;
import com.kkb.spring.resource.Resource;
import com.ysc.springmvc.handler.inter.HttpRequestHandler;
import com.ysc.springmvc.handler.inter.SimpleControllerHandler;
import com.ysc.springmvc.handleradapter.HttpRequestHandlerAdapter;
import com.ysc.springmvc.handleradapter.SimpleControllerHandlerAdapter;
import com.ysc.springmvc.handleradapter.inter.HandlerAdapter;
import com.ysc.springmvc.handlermapping.BeanUrlHandlerMapping;
import com.ysc.springmvc.handlermapping.SimpleUrlHandlerMapping;
import com.ysc.springmvc.handlermapping.inter.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public class DispatcherService extends AbstractHttpServlet {
    private BeanDefinitionRegistry beanFactory;
    private List<HandlerMapping> handlerMappingList = new ArrayList<>();
    private List<HandlerAdapter> handlerAdapterList = new ArrayList<>();

    public DispatcherService() throws ServletException {
        init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        initSpringContainer(config);
        initStrategy();
    }

    private void initStrategy() {
        handlerMappingList = beanFactory.getBeansByType(HandlerMapping.class);
        handlerAdapterList = beanFactory.getBeansByType(HandlerAdapter.class);
    }

    /**
     * spring容器初始化
     */
    private void initSpringContainer(ServletConfig config) {
        beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 获取web.xml中的init-param参数
        String location = config.getInitParameter("contextConfigLocation");
        Resource resource = new ClasspathResource(location);
        InputStream inputStream = resource.getResource();

        reader.loadBeanDefinitions(inputStream);
    }

    @Override
    protected void dispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1、通过handlerMapping获取handler
        Object handler = getHandler(req, resp);
        if (handler == null) {
            return;
        }
        // 2、获取handlerAdapter
        HandlerAdapter handlerAdapter = getHandlerAdapter(handler, req, resp);
        if (handlerAdapter == null) {
            return;
        }
        // 3、执行handler
        handlerAdapter.handlerRequest(handler, req, resp);
        // 4、ViewResolve解析modelAndView
    }

    private HandlerAdapter getHandlerAdapter(Object handler, HttpServletRequest req, HttpServletResponse resp) {
        for (HandlerAdapter handlerAdapter : handlerAdapterList) {
            if (handlerAdapter.acceptAble(handler)) {
                return handlerAdapter;
            }
        }
        System.out.println("无对应handlerAdapter");
        return null;
    }

    private Object getHandler(HttpServletRequest req, HttpServletResponse resp) {
        for (HandlerMapping handlerMapping : handlerMappingList) {
            Object handler = handlerMapping.getHandler(req);
            if (handler != null) {
                return handler;
            }
        }
        System.out.println("无对应handler");
        return null;
    }

}
