/**
 * 文件名: Main.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-03 22:17
 */
public class AnnotationMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        DemoService service = context.getBean(DemoService.class);

        service.outputResult();

        context.close();
    }
}
