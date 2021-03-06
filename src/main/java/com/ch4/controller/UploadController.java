/**
 * 文件名: UploadContrller.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.controller;

import com.ch0.excel.ImportExcel;
import com.ch0.excel.ImportResultData;
import com.ch0.excel.ParseExcelUtil;
import com.ch4.domain.DemoExcel;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-06-23 23:24
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String upload(MultipartFile file) {

        /**
         * MultipartFile 接受上传的文件
         * FileUtils.writeByteArrayToFile 快速写到磁盘
         */
        try {
            FileUtils.writeByteArrayToFile(new File("D:/logs/" + file.getOriginalFilename()), file.getBytes());

            ImportResultData importResultData = new ParseExcelUtil<>(DemoExcel.class).parseFile(
                    ImportExcel.CMF_CLM_CLAIM_STATE_APPLY_IMPORT_MAP, file);
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        } catch (Exception e) {
            e.printStackTrace();
            return "wrong";
        }

    }
}
