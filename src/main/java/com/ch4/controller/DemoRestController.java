/**
 * 文件名: DemoRestController.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.controller;

import com.ch4.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-15 11:34
 */
@RestController
@RequestMapping("rest")
public class DemoRestController {

    @RequestMapping(value = "/getjson", produces = {"application/json;charset=UTF-8"})
    public DemoObj getjson(DemoObj obj) {
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");
    }

    @RequestMapping(value = "/getxml", produces = {"application/xml;charset=UTF-8"})
    public DemoObj getxml(DemoObj obj) {
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");
    }
}
