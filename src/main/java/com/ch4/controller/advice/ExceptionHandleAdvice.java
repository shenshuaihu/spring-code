/**
 * 文件名: ExceptionHandleAdvice.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: 定制 controllerAdvice
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-20 23:09
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandleAdvice {

    /**
     * @param exception
     * @param request
     * @return
     * @ ExceptionHandler定义了全局处理，通过value属性过滤拦截条件，这里是拦截所以的exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        log.info(exception.getMessage().toString());
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    /**
     * 将注解ModelAttribute将键值对添加到全局，所以注解@RequestMapping的方法可获取此键值对
     *
     * @param model
     */
    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("msg", "额外信息");
    }

    /**
     * 通过InitBinder定制WebDataBinder
     *
     * @param webDataBinder
     */
    @InitBinder
    public void initBonder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }
}
