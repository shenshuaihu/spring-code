/**
 * 文件名: ServiceMonitor.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch0.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * @description:
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-07-01 20:42
 */

@BTrace
public class ServiceMonitor {

    @OnMethod(clazz = "com.test.captcha.CaptchaFactory", method = "getCaptcha", location = @Location(Kind.RETURN))
    public static void printMethodRunTime(int length, int level, @Return AnyType result) {
        println("length: " + length + " ,level: " + level);
        println("result : " + result);
    }
}