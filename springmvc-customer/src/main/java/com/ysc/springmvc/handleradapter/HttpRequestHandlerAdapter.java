package com.ysc.springmvc.handleradapter;

import com.ysc.springmvc.handler.inter.HttpRequestHandler;
import com.ysc.springmvc.handleradapter.inter.HandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public class HttpRequestHandlerAdapter implements HandlerAdapter {
    @Override
    public void handlerRequest(Object handler, HttpServletRequest request, HttpServletResponse response) {
        HttpRequestHandler realHandler = (HttpRequestHandler) handler;
        realHandler.handlerRequest(request, response);
    }

    @Override
    public boolean acceptAble(Object handler) {
        return handler instanceof HttpRequestHandler;
    }
}
