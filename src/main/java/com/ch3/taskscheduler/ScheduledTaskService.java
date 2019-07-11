/**
 * 文件名: ScheduledTaskService.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.taskscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 计划任务执行类
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-28 23:29
 */
@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private AtomicInteger number = new AtomicInteger();


    /**
     * 通过@Scheduled声明改方法是计划任务，使用fixedRate属性每隔固定时间执行
     */
    /**
     * 周期性执行任务
     * <p>
     * 假设:
     * 执行一次任务需要消耗的时间为 exeTime
     * 执行此次任务的开始时间是 nowTime
     * 执行下一次任务的实际时间是 actuallyTime
     * 如果 exeTime >= fixedRate ，那么，actuallyTime >= nowTime + exeTime;
     * 如果 exeTime < fixedRate ， 那么，actuallyTime >= nowTime + fixedRate;
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("每隔五秒执行一次 fixedRate " + dateFormat.format(new Date()));
    }

    /**
     * cron 属性可按照指定时间执行 每七秒执行一次
     */
    @Scheduled(cron = "0/7 10 * * * ? ")
    public void fixTimeExecution() {
        System.out.println("在指定时间执行 " + dateFormat.format(new Date()));
    }

    /**
     * 通过@Scheduled声明改方法是计划任务，使用fixedDelay属性每隔固定时间执行
     * 间隔时间是根据上次任务开始的时候计时的，即使方法执行方法时间+fixedDelay
     * actuallyTime  = nowTime + exeTime + fixedDelay;
     */

    @Scheduled(fixedDelay = 5000)
    public void report2CurrentTime() {
        LocalTime start = LocalTime.now();

        //前面和末尾几个字符串是用来改变打印的颜色的
        System.out.println("\033[31;4m" + Thread.currentThread() + " start " + number.incrementAndGet()
                + " @ " + start + "\033[0m");

        /**
         * 使用ThreadLocalRandom.current() 防止并发时造成随机数生成的没有随机性
         */
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(15) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("每隔五秒执行一次 fixedDelay " + dateFormat.format(new Date()));

        LocalTime end = LocalTime.now();

        System.out.println(Thread.currentThread() + " end " + number.get() + " @ " +
                end + ", seconds cost " + (ChronoUnit.SECONDS.between(start, end)));

    }

}
