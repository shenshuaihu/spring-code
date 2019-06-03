/**
 * 文件名: DemoService.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch3.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 演示服务Bean
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-03 22:03
 */
@Service
public class DemoService {

    public void outputResult() {
        System.out.println("通过组合注释配置获得Bean");
    }
}
