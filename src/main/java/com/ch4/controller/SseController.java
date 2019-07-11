/**
 * 文件名: SseController.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @description: 服务器端推送控制器
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-25 23:29
 */
@Controller
public class SseController {

    /**
     * 输出类型 text/event-stream 是对服务器端SSE的支持
     * 此处每5s向浏览器推送随机消息
     *
     * @return
     */
    @RequestMapping(value = "/push", produces = "text/event-stream")
    public @ResponseBody
    String push() {
        Random random = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3: " + random.nextInt() + "\n\n";
    }
}
