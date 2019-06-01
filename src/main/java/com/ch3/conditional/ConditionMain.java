/**
 * 文件名: ConditionMain.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-30 23:22
 */
public class ConditionMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConditionConfig.class);

        ListService listService = context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name")
                + " 系统下的列表命令为： "
                + listService.showListCmd()
        );
    }
}
