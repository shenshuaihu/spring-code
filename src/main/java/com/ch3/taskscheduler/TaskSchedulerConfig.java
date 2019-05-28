/**
 * 文件名: TaskSchedulerConfig.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.taskscheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: 计划任务配置类
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-28 23:38
 *  注解 @EnableScheduling 开启对计划任务的支持
 */
@Configuration
@ComponentScan("com.ch3.taskscheduler")
@EnableScheduling
public class TaskSchedulerConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
    }
}
