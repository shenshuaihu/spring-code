/**
 * 文件名: AwareMain.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: 运行
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-25 00:36
 */
public class AwareMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareService awareService = context.getBean(AwareService.class);
        awareService.outputResult();
        context.close();
    }
}
