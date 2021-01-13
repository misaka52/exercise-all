package com.ysc.springmvc.handler;

import com.ysc.springmvc.handler.inter.SimpleControllerHandler;
import com.ysc.springmvc.modelandview.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuanshancheng
 * @date 2021/1/10
 */
public class QueryGradeHandler implements SimpleControllerHandler {
    @Override
    public ModelAndView handlerRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("userName=zhangsan,chinese=70,math=94,english=76");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
