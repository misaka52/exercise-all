package com.ysc.springmvc.handler.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public interface HttpRequestHandler {
    void handlerRequest(HttpServletRequest request, HttpServletResponse response);
}
