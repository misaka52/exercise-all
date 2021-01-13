package com.ysc.springmvc.handlermapping;

import com.ysc.springmvc.handlermapping.inter.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public class SimpleUrlHandlerMapping implements HandlerMapping {
    @Override
    public Object getHandler(HttpServletRequest request) {
        return null;
    }
}
