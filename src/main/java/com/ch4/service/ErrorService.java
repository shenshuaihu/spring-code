/**
 * 文件名: ErrorService.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.service;

import org.springframework.stereotype.Service;

/**
 * @description:  异常信息测试
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-07-17 12:06
 */
@Service
public class ErrorService {

    public String sqlService() {

        int a = 1/0;
        return "1";
    }
}
