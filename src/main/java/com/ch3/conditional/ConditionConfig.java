/**
 * 文件名: ConditionConfig.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 配置类
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-30 23:20
 */
@Configuration
@ComponentScan("com.ch3.conditional")
public class ConditionConfig {

    /**
     * 通过 @Conditional 符号Windows条件则实例化 windowsListService
     *
     * @return
     */
    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsListService() {
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxListService() {
        return new LinuxListService();
    }
}
