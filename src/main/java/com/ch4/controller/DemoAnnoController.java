/**
 * 文件名: DemoAnnoController.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.controller;

import com.ch4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-15 11:03
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

    /**
     *  此方法为标注路径，使用了类级别的路径/anno;
     *  produce 可定制返回的response的媒体类型和字符集，或需返回的json对象，可设置为 product = "application/json;charset=UTF-8
     *
     * @param request
     * @return
     */
    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public @ResponseBody String index(HttpServletRequest request) {
        return "url:" + request.getRequestURI() + "can access";
    }

    @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String demoPathVar(@PathVariable String str, HttpServletRequest request) {
        return "url:" + request.getRequestURI() + " can access";
    }

    @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String passRequestParam(Long id, HttpServletRequest request) {
        return "url:" + request.getRequestURI() + "can access,id: " + id;
    }

    @RequestMapping(value = "/obj", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String passObj(DemoObj obj, HttpServletRequest request) {
        return "url:" + request.getRequestURI() + "can access, " +
                "obj id: " + obj.getId() + "  obj name: " + obj.getName();
    }

    @RequestMapping(value = {"name1", "name2"}, produces = "text/plain;charset=UTF-8")
    public @ResponseBody String remove(HttpServletRequest request) {
        return "url:" + request.getRequestURI() + "can access";
    }
}
