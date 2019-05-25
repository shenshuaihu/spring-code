/**
 * 文件名: TaskExecutorConfig.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.taskexecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @description: 多线程 配置类
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-25 11:41
 */
@Configuration
@ComponentScan("com.ch3.taskexecutor")
@EnableAsync    // 开启异步任务支持
public class TaskExecutorConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        /**
         *  创建线程池
         *      核心线程数
         *      最大线程数
         *      队列最大长度
         */
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
