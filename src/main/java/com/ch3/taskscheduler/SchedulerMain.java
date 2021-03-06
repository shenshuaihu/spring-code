/**
 * 文件名: SchedulerMain.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.taskscheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: 定时计划启动入口
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-28 23:43
 */
public class SchedulerMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
    }
}
