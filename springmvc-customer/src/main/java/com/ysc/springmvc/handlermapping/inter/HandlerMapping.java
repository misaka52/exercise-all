package com.ysc.springmvc.handlermapping.inter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public interface HandlerMapping {
    Object getHandler(HttpServletRequest request);
}
