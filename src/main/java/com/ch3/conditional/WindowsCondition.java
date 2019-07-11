/**
 * 文件名: WindowsCondition.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @description: 判断条件定义，判断Windows的条件
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-30 23:12
 */
public class WindowsCondition implements Condition {

    /**
     * 如果返回的为True，改内容会被创建
     *
     * @param conditionContext
     * @param annotatedTypeMetadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getEnvironment().getProperty("os.name").contains("Windows");
    }
}
