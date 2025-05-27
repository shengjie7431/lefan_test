package com.lefancrm.apicenter.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jani on 2017/10/19.
 */
public class PreInterceptor  extends HandlerInterceptorAdapter {
    /*
            * 请求处理之前被拦截
    */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("请求处理之前被拦截请求处理之前被拦截请求处理之前被拦截请求处理之前被拦截请求处理之前被拦截");
        response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.139:9527");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("P3P", "CP=CAO PSA OUR");
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
            response.addHeader("Access-Control-Max-Age", "3600");
        }
        return true;
    }
}
