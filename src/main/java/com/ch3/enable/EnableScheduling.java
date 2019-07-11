/**
 * 文件名: EnableScheuling.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.enable;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.SchedulingConfiguration;

/**
 * @description: 第一类 直接导入配置类
 * 导入了 SchedulingConfiguration
 * 开启了  Configuration
 * 注册了 ScheduledAnnotationBeanPostProcessor Bean
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-03 23:20
 */


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SchedulingConfiguration.class})
@Documented
public @interface EnableScheduling {
}