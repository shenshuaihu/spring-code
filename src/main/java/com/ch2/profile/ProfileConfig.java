/**
 * 文件名: ProfileConfig.java
 * 版权：Copyright 2018-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch2.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @description:
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-01 11:24
 */
@Configuration
public class ProfileConfig {

    /**
     * Profile 为dev时，实例化devDemoBean 开发环境
     *
     * @return
     */
    @Bean
    @Profile("dev")
    public DemoBean devDemoBean() {
        return new DemoBean("from development profile");
    }

    /**
     * Profile 为prod时，实例化prodDemoBean  生成环境
     *
     * @return
     */
    @Bean
    @Profile("prod")
    public DemoBean prodDemoBean() {
        return new DemoBean("from production profile");
    }
}
