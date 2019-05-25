/**
 * 文件名: AwareService.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述:
 */
package com.ch3.aware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @description: 依赖注入的使用
 *      继承之后重新
 *      BeanNameAware 获取到容器中Bean的名称
 *      ResourceLoaderAware 获得记载器
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-05-25 00:24
 */
@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {

    private String beanName;
    private ResourceLoader loader;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

    public void outputResult() {
        System.out.println("Bean的名称为：" + beanName);
        Resource resource = loader.getResource("classpath:com/ch3/aware/test.txt");

        try {
            System.out.println("加载文件内容为：" + IOUtils.toString(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
