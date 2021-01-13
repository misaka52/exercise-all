package com.ysc.springmvc.handler.inter;

import com.ysc.springmvc.modelandview.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public interface SimpleControllerHandler {
    ModelAndView handlerRequest(HttpServletRequest request, HttpServletResponse response);
}
