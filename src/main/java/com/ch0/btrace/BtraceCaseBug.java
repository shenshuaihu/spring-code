/**
 * 文件名: BtraceCase.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch0.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

import com.sun.btrace.annotations.Export;

import java.util.Random;

/**
 * @description: 执行add方法时，对传入参数、返回值以及执行耗时进行分析，btrace脚本：
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-07-08 16:37
 */
@BTrace
public class BtraceCaseBug {

    @Export
    static long counter;

    @OnMethod(clazz = "com.ch0.btrace.BtraceCase", method = "add", location = @Location(Kind.RETURN))
    public static void run(@Self Object self, int a, int b, @Return int result, @Duration long time) {
        println("parameter: a=" + a + ", b=" + b);
        println("cost time: " + time);
        counter++;
    }

    @OnTimer(1000)
    public static void run() {
        println("execute coount: " + counter);
    }

}
