package com.ysc.springmvc.handler;

import com.ysc.springmvc.handler.inter.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public class QueryUserHandler implements HttpRequestHandler {
    @Override
    public void handlerRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("userName=zhangsan, age=19");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
