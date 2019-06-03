package com.ch3.enable;


import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *    第三类：动态注册Bean
 *       AspectJAutoProxyRegistrar 事先实现了ImportBeanDefinitionRegistrar 接口
 *       作用是在运行时自动添加Bean到已有的配置类，通过重写 registerBeanDefinitions(AnnotationMetadata var1, BeanDefinitionRegistry var2)
 *       AnnotationMetadata 获取当前配置类上的注解，BeanDefinitionRegistry 用来注册Bean
 *
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {

    boolean proxyTargetClass() default false;

}
