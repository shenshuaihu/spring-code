/**
 * 文件名: HelloWorld.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch0.btrace;
// import all BTrace annotations

import com.sun.btrace.annotations.*;
// import statics from BTraceUtils class
import static com.sun.btrace.BTraceUtils.*;

/**
 * @description:
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-28 10:39
 */

// @BTrace annotation tells that this is a BTrace program
@BTrace
public class HelloWorld {

    // @OnMethod annotation tells where to probe.
    // In this example, we are interested in entry
    // into the Thread.start() method.
    @OnMethod(
            clazz = "java.lang.Thread",
            method = "start"
    )
    public static void func() {
        // println is defined in BTraceUtils
        // you can only call the static methods of BTraceUtils
        println("about to start a thread!");
    }
}