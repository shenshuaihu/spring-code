/**
 * 文件名: ImportExcel.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch0.excel;

import com.ch4.domain.DemoExcel;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * @description:
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-07-03 10:30
 */
public class ImportExcel {

    /**
     * 新预付核销托收流水导入Excel模板导入维护 CmfClmClaimStateApply
     */
    public static final LinkedHashMap<String, String> CMF_CLM_CLAIM_STATE_APPLY_IMPORT_MAP =
            new LinkedHashMap<String, String>() {
                {
                    put("Sheet1->行号(必输)", "lineNum");
                    put("Sheet1->流水号(必输)", "bankSeqNumber");
                    put("Sheet1->流水金额(必输)", "statementAmount");
                    put("Sheet1->本次核销金额(必输)", "amount");
                }
            };

    public static void main(String[] args) {
        File file = new File("");
       /* ImportResultData  importResultData = new ParseExcelUtil<>(DemoExcel.class).parseFile(
                ImportExcel.CMF_CLM_CLAIM_STATE_APPLY_IMPORT_MAP, file);*/
    }
}
