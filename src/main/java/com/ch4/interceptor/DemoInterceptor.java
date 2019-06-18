/**
 * 文件名: DemoInterceptor.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 拦截器 实现对每一个请求   继承HandlerInterceptorAdapter 可以自定义拦截器
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-15 13:00
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {

    /**
     * 请求发生前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    /**
     * 请求发生后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        request.removeAttribute("startTime");
        System.out.println("本次请求处理时间为：" + new Long(endTime - startTime));
        request.setAttribute("handlingTime", endTime - startTime);
    }
}
