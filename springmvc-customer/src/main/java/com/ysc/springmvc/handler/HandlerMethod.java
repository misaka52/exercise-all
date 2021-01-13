package com.ysc.springmvc.handler;

import com.ysc.springmvc.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * @author yuanshancheng
 * @date 2021/1/12
 */
public class HandlerMethod {
    private Object controller;
    private Method method;

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public HandlerMethod(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }
}
