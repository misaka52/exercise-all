package com.ysc.springmvc.handleradapter;

import com.alibaba.fastjson.JSON;
import com.ysc.springmvc.annotation.ResponseBody;
import com.ysc.springmvc.handler.HandlerMethod;
import com.ysc.springmvc.handleradapter.inter.HandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuanshancheng
 * @date 2021/1/12
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {
    /**
     *
     * @param handler
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    public void handlerRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Object[] args = resolveParams(request, handlerMethod);
        Method method = handlerMethod.getMethod();
        Object returnValue = method.invoke(handlerMethod.getController(), args);

        handlerValue(response, returnValue, handlerMethod);
    }

    private Object[] resolveParams(HttpServletRequest request, HandlerMethod handlerMethod) {
        List<Object> argList = new ArrayList<>();
        Method method = handlerMethod.getMethod();

        Parameter[] parameters = method.getParameters();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Parameter parameter : parameters) {
            // 参数值
            String[] values = parameterMap.get(parameter.getName());
            if (values == null || values.length == 0) {
                continue;
            }
            // 参数类型
            Class<?> type = parameter.getType();
            argList.add(transform(values[0], type));
        }
        return argList.toArray();
    }

    private Object transform(String value, Class<?> type) {
        if (type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == String.class) {
            return value;
        } else {
            // todo 补充类型转换
            return null;
        }
    }

    private void handlerValue(HttpServletResponse response, Object returnValue, HandlerMethod handlerMethod) throws IOException {
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(ResponseBody.class)) {
            if (returnValue instanceof Map) {
                response.setContentType("application/json;charset=utf8");
                response.getWriter().write(JSON.toJSONString(returnValue));
            } else if (returnValue instanceof String) {
                response.setContentType("text/plain;charset=utf8");
                response.getWriter().write(returnValue.toString());
            } else {
                // 默认
                response.setContentType("application/json;charset=utf8");
                response.getWriter().write(JSON.toJSONString(returnValue));
            }
        } else {
            // 视图处理
        }
    }

    private Object[] getArgs() {
        return null;
    }

    @Override
    public boolean acceptAble(Object handler) {
        return handler instanceof HandlerMethod;
    }
}
