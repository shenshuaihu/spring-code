/**
 * 文件名: ScheduledTaskService.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.taskscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:  计划任务执行类
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-28 23:29
 */
@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 通过@Scheduled声明改方法是计划任务，使用fixedRate属性每隔固定时间执行
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("每隔五秒执行一次 " + dateFormat.format(new Date()));
    }

    /**
     * cron 属性可按照指定时间执行 每七秒执行一次
     */
    @Scheduled(cron = "0/7 * * * * ? ")
    public void fixTimeExecution() {
        System.out.println("在指定时间执行 " + dateFormat.format(new Date()));
    }
}
