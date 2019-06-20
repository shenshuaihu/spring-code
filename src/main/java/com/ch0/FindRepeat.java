/**
 * 文件名: FindRepeat.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 查询数据重复的是哪一行
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-18 15:30
 */
public class FindRepeat {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();

        list.add("123456");
        list.add("123455");
        list.add("123454");
        list.add("123453");
        list.add("123451");
        list.add("123451");
        list.add("123456");
        StringBuffer buffer = new StringBuffer();
        for (String number : list) {
            if (set.contains(number)) {
                buffer.append("数据" + number);
            } else {
                set.add(number);
            }
        }

        buffer.append("重复");
        System.out.println(buffer.toString());

    }
}
