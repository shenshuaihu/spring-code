/**
 * 文件名: BtraceCase.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch0.btrace;

import java.util.Random;

/**
 * @description: 执行add方法时，对传入参数、返回值以及执行耗时进行分析，btrace脚本：
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-07-08 16:37
 */
public class BtraceCase {
    public static Random random = new Random();
    public int size;

    public static void main(String[] args) throws Exception {
        new BtraceCase().run();
    }

    public void run() throws Exception {
        while (true) {
          int a =  add(random.nextInt(10), random.nextInt(10));
            System.out.println(a);
        }
    }

    public int add(int a, int b) throws Exception {
        Thread.sleep(random.nextInt(10) * 100);
        return a + b;
    }
}
