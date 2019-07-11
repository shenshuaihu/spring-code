/**
 * 文件名: DemoObj.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用于获取request对象参数和返回此对象到response
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-15 10:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoObj {
    private Long id;
    private String name;
}
