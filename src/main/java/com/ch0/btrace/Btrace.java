/**
 * 文件名: Btrace.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch0.btrace;

import com.sun.btrace.BTraceUtils;

import static com.sun.btrace.BTraceUtils.*;

import com.sun.btrace.annotations.*;

/**
 * @description:
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-28 10:18
 */
@BTrace
public class Btrace {

    @OnMethod(
            clazz = "com.jiuyan.message.controller.AdminController",
            method = "sayHello",
            location = @Location(Kind.RETURN)//函数返回的时候执行，如果不填，则在函数开始的时候执行
    )
    public static void sayHello(String name, int age, @Return String result) {
        println("name: " + name);
        println("age: " + age);
        println(result);
    }

    @OnMethod(
            clazz = "com.jiuyan.message.controller.AdminController",
            method = "sayHello",
            location = @Location(Kind.RETURN)
    )
    public static void sayHello(@Duration long duration) {//单位是纳秒，要转为毫秒
        println(strcat("duration(ms): ", str(duration / 1000000)));
    }

}
