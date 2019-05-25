/**
 * 文件名: Executor.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.taskexecutor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @description: 任务执行类
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-25 10:39
 */
@Service
public class AsyncTaskService {

    /**
     *  Async 异步方法
     * @param i
     */
    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务：" + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1：" + i);
    }
}
