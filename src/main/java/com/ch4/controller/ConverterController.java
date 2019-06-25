/**
 * 文件名: ConverterController.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.controller;

import com.ch4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-25 08:37
 */

@Controller
public class ConverterController {

    @RequestMapping(value = "/convert", produces = {"application/x-wisely"})
    public @ResponseBody DemoObj converter(@RequestBody DemoObj demoObj) {
        return demoObj;
    }
}
