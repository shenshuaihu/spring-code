/**
 * 文件名: HelloController.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

/**
 * @description: 简单控制器
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-13 23:32
 */
@Controller
public class HelloController {
   // @RequestMapping("/index")
    public String hello() {
        return "index";
    }
}
