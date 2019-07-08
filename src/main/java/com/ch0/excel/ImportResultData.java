/**
 * 文件名：ImportResultData.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述：导入结果类
 */
package com.ch0.excel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * 导入结果类
 *
 * @author zhixiong.shen@hand-china.com
 * @date 2018/6/8
 */
public class ImportResultData {

    /**
     * 解析成功标识
     */
    @JsonInclude(Include.NON_NULL)
    private boolean parseSuccess;

    /**
     * 解析失败信息
     */
    @JsonInclude(Include.NON_NULL)
    private String parseMessage;

    /**
     * 校验成功标识
     */
    @JsonInclude(Include.NON_NULL)
    private boolean validateSuccess;

    /**
     * 校验失败信息
     */
    @JsonInclude(Include.NON_NULL)
    private List<Map<String, String>> excelErrorList;

    /**
     * 全部对象集合（校验通过）
     */
    @JsonInclude(Include.NON_NULL)
    private List<?> allList;

    /**
     * 每个sheet页的名称（校验通过）
     */
    @JsonInclude(Include.NON_NULL)
    private List<String> sheetNameList;

    /**
     * 每个sheet页的记录数（校验通过）
     */
    @JsonInclude(Include.NON_NULL)
    private List<Integer> recordCountOfSheet;

    /**
     * 校验失败的总行数
     */
    @JsonInclude(Include.NON_NULL)
    private Long total;

    /**
     * 导入成功标识
     */
    @JsonInclude(Include.NON_NULL)
    private boolean importSuccess;

    /**
     * 导入结果信息
     */
    @JsonInclude(Include.NON_NULL)
    private String importMessage;


    private List<String> titleNameList;


    public ImportResultData() {
    }

    public boolean isParseSuccess() {
        return this.parseSuccess;
    }

    public void setParseSuccess(boolean parseSuccess) {
        this.parseSuccess = parseSuccess;
    }

    public String getParseMessage() {
        return this.parseMessage;
    }

    public void setParseMessage(String parseMessage) {
        this.parseMessage = parseMessage;
    }

    public boolean isValidateSuccess() {
        return this.validateSuccess;
    }

    public void setValidateSuccess(boolean validateSuccess) {
        this.validateSuccess = validateSuccess;
    }

    public List<Map<String, String>> getExcelErrorList() {
        return this.excelErrorList;
    }

    public void setExcelErrorList(List<Map<String, String>> excelErrorList) {
        this.excelErrorList = excelErrorList;
        if (excelErrorList instanceof Page) {
            this.setTotal(Long.valueOf(((Page) excelErrorList).getTotal()));
        } else {
            this.setTotal(Long.valueOf((long) excelErrorList.size()));
        }
    }

    public List<?> getAllList() {
        return this.allList;
    }

    public void setAllList(List<?> allList) {
        this.allList = allList;
    }

    public List<String> getSheetNameList() {
        return this.sheetNameList;
    }

    public void setSheetNameList(List<String> sheetNameList) {
        this.sheetNameList = sheetNameList;
    }

    public List<Integer> getRecordCountOfSheet() {
        return this.recordCountOfSheet;
    }

    public void setRecordCountOfSheet(List<Integer> recordCountOfSheet) {
        this.recordCountOfSheet = recordCountOfSheet;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public boolean isImportSuccess() {
        return this.importSuccess;
    }

    public void setImportSuccess(boolean importSuccess) {
        this.importSuccess = importSuccess;
    }

    public String getImportMessage() {
        return this.importMessage;
    }

    public void setImportMessage(String importMessage) {
        this.importMessage = importMessage;
    }

    public List<String> getTitleNameList() {
        return titleNameList;
    }

    public void setTitleNameList(List<String> titleNameList) {
        this.titleNameList = titleNameList;
    }
}
