package com.ysc.springmvc.handleradapter.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public interface HandlerAdapter {
    void handlerRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception;

    boolean acceptAble(Object handler);
}
