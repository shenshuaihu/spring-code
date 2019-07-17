/**
 * 文件名: AdviceController.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.controller.advice;

import com.ch4.domain.DemoObj;
import com.ch4.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 演示控制器
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-20 23:20
 */
@RestController
public class AdviceController {

    @Autowired
    private ErrorService errorService;

    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj) {
        throw new IllegalArgumentException("非常抱歉，参数有误/" + "来自 @ModelAttribute："
                + msg + obj.toString());
    }



    @RequestMapping("/sqlerror")
    public String testSqlError(){

        try {
            String a = errorService.sqlService();
        } catch (Exception e) {
          //  return "请求失败！";
        }

        return errorService.sqlService();
    }

}
