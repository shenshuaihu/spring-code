/**
 * 文件名: MyMessageConverter.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.messageconverter;

import com.ch4.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @description: 自定义MessageConverter
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-24 23:31
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    /**
     * 自定义的媒体类型 application/x-wisely
     */
    public MyMessageConverter() {
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    /**
     * HttpMessageConverter 只处理 DemoObj类
     * @param aClass
     * @return
     */
    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    /**
     * 重写 readInternal 处理请求数据 。此处由‘-’隔开的数据，转化DemoObj对象
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
       return new DemoObj(new Long(tempArr[0]), tempArr[1]);
    }

    /**
     *  重写writeInternal 处理出如何输出数据到response 此处在原样中输出加上“hello”
     * @param obj
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        String out = "hello:" + obj.getId() + "-" +obj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
