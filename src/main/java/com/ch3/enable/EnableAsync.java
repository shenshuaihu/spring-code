package com.ch3.enable;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;

/**
 * 第二类：依据条件选择配置类
 * AsyncConfigurationSelector 通过条件来选择需要导入的配置类
 * 继承 AdviceModeImportSelector  又实现了ImportSelector接口
 * 接口重写selectImports方法 进行事先条件判断
 * PROXY 或者 ASPECTJ 选择不同的配置类
 *
 * @author ShenShuaihu
 * @version 1.0
 * @date 23:26
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AsyncConfigurationSelector.class})
public @interface EnableAsync {
    Class<? extends Annotation> annotation() default Annotation.class;

    boolean proxyTargetClass() default false;

    AdviceMode mode() default AdviceMode.PROXY;

    int order() default 2147483647;
}
