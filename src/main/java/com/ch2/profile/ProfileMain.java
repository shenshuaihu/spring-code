/**
 * 文件名: ProfileMain.java
 * 版权：Copyright 2018-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: profilec测试启动入口
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-01 11:51
 */
public class ProfileMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        /**
         * 1、先将活动的profile设置为prod
         * 2、后注册Bean配置类，不然会报Bean未定义的错误
         * 3、刷新容器
         */
        context.getEnvironment().setActiveProfiles("prod");
        context.register(ProfileConfig.class);
        context.refresh();
        DemoBean demoBean = context.getBean(DemoBean.class);

        System.out.println(demoBean.getContent());

        context.close();
    }
}
