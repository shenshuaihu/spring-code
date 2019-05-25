/**
 * 文件名: TaskMain.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: 线程调用入口
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-25 13:13
 */
public class TaskMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService taskService = context.getBean(AsyncTaskService.class);
        System.out.println(taskService);
        for (int i = 0; i < 10; i++) {
            taskService.executeAsyncTask(i);
            taskService.executeAsyncTaskPlus(i);
        }
        context.close();
    }
}

