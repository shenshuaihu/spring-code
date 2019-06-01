/**
 * 文件名: LinuxCondition.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @description: 判断添加定义，判断Linux的条件
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-30 23:15
 */
public class LinuxCondition implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            return conditionContext.getEnvironment().getProperty("os.name").contains("Linux");
        }
    }
