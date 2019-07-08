/**
 * 文件名：ParseExcelUtil.java
 * 版权：Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述：解析Excel文件
 */
package com.ch0.excel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析Excel文件
 *
 * @param <T> 泛型参数
 */
public final class ParseExcelUtil<T> {

    private static Logger log = LoggerFactory.getLogger(ParseExcelUtil.class);

    private Class<T> clazz;

    public ParseExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    private static final String DOT_ZERO = ".0";
    private static final int NUMBER_TWO = 2;
    private static NumberFormat numberFormat = NumberFormat.getInstance();
    static {
        numberFormat.setGroupingUsed(false);
    }

    /**
     * 解析Excel文件
     *
     * @param map            excel与对象属性对应map
     * @param file           待解析文件
     * @return 对象集合
     * @throws Exception 异常
     */
    public ImportResultData parseFile( Map<String, String> map,
                                       MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        log.debug("Excel:" + fileName);
        ImportResultData importResultData = new ImportResultData();
        // 校验错误消息
        List<Map<String, String>> excelErrorList = new ArrayList<>();
        // 将文件内容解析为对象集合
        List<T> list = new ArrayList<>();
        Workbook book = WorkbookFactory.create(file.getInputStream());
        // sheet数量
        int countOfSheet = book.getNumberOfSheets();
        // 每个sheet页的记录数
        List<Integer> recordCountOfSheet = new ArrayList<>();
        // 每个sheet页的名称
        List<String> sheetNameList = new ArrayList<>();
        if (countOfSheet > 0) {
            for (int i = 0; i < countOfSheet; i++) {
                Sheet sheet = book.getSheetAt(i);
                // 得到sheet页名称
                String sheetName = sheet.getSheetName();
                sheetNameList.add(sheetName);
                // 得到标题行
                Row title = sheet.getRow(0);
                // 得到数据的行数
                int rowCount = sheet.getLastRowNum();

                int colNum = sheet.getRow(0).getPhysicalNumberOfCells(); //获取列数

                //去除上传excel中存在空行问题
                rowCount = getInviliadAllRows(sheet, colNum, rowCount, NUMBER_TWO);

                recordCountOfSheet.add(rowCount);
                // 定义一个map用于存放Excel列名和对象field
                Map<String, Field> fieldsMap = null;
                try {
                    fieldsMap = getFiledMap(map, clazz, fileName, sheetName, title);
                    if (rowCount > 0) {
                        int colCount = title.getLastCellNum();
                        for (int row = 1; row <= rowCount; row++) {
                            T entity = clazz.newInstance();
                            Row rowData = sheet.getRow(row);
                            Map<String, String> lineErrorMap = new HashMap<>();
                            for (int col = 0; col < colCount; col++) {
                                Cell cell = rowData.getCell(col);
                                String titleName = sheetName + "->" + title.getCell(col).toString().trim();
                                Field field = fieldsMap.get(titleName);
                                if (cell != null) {
                                    entity = setEntity(row + 1, field, cell, entity, sheetName, titleName,
                                            lineErrorMap);
                                }
                            }
                            list.add(entity);
                            if (!lineErrorMap.isEmpty()) {
                                excelErrorList.add(lineErrorMap);
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error(e.toString());
                    importResultData.setParseSuccess(false);
                    importResultData.setParseMessage(e.toString());
                    return importResultData;
                } finally {
                    book.close();
                }
            }
        } else {
            book.close();
            importResultData.setParseSuccess(false);
            importResultData.setParseMessage("Excel\"" + fileName + "\"中不存在sheet页，请核实");
            return importResultData;
        }
        if (excelErrorList.isEmpty()) {
            importResultData.setParseSuccess(true);
            importResultData.setValidateSuccess(true);
            importResultData.setAllList(list);
            importResultData.setRecordCountOfSheet(recordCountOfSheet);
            importResultData.setSheetNameList(sheetNameList);
            return importResultData;
        } else {
            importResultData.setParseSuccess(true);
            importResultData.setValidateSuccess(false);
            importResultData.setExcelErrorList(excelErrorList);
            return importResultData;
        }
    }

    /**
     * 校验Excel标题行并且得到Excel列和对象字段map
     *
     * @param map       Excel与对象对应关系map
     * @param clazz     对象类
     * @param fileName  Excel名称
     * @param sheetName sheet名称
     * @param title     标题行
     * @return 结果
     * @throws Exception 异常
     */
    private static Map<String, Field> getFiledMap(Map<String, String> map, Class clazz, String fileName,
                                                  String sheetName, Row title) throws Exception {
        Set<String> titleSet = new HashSet<>();
        Map<String, Field> fieldMap = new HashMap<>();
        if (title != null) {
            int cellCount = title.getLastCellNum();
            for (int i = 0; i < cellCount; i++) {
                String realTitleName = title.getCell(i).toString().trim();
                String titleName = sheetName + "->" + title.getCell(i).toString().trim();
                if (title.getCell(i) == null || titleName.endsWith("->")) {
                    throw new RuntimeException("Excel\"" + fileName + "\"中\"" + sheetName + "\"页中存在空列，请核实");
                } else {
                    if (titleSet.contains(realTitleName)) {
                        throw new RuntimeException("Excel\"" + fileName + "\"中\"" + sheetName + "\"页中\""
                                + realTitleName + "\"列重复，请核实");
                    } else {
                        titleSet.add(realTitleName);
                        String fieldName = map.get(titleName);
                        if (StringUtils.isEmpty(fieldName)) {
                            throw new RuntimeException("Excel\"" + fileName + "\"中\"" + sheetName + "\"页中\""
                                    + realTitleName + "\"列无法识别，请核实");
                        }
                        Field field = clazz.getDeclaredField(fieldName);
                        field.setAccessible(true);
                        fieldMap.put(titleName, field);
                    }
                }
            }
        } else {
            throw new RuntimeException("Excel\"" + fileName + "\"中\"" + sheetName + "\"页中不存在标题行，请核实");
        }
        return fieldMap;
    }

    /**
     * 将表格中的单元格set到实体类中
     *
     * @param lineNum      行号
     * @param field        字段
     * @param cell         Excel表格单元格(数据来源)
     * @param entity       待填充的实体
     * @param sheetName    sheet页名称
     * @param titleName    标题
     * @param lineErrorMap 错误消息
     * @return entity 填充好的实体
     */
    private T setEntity(int lineNum, Field field, Cell cell, T entity, String sheetName, String titleName,
                        Map<String, String> lineErrorMap) {
        Class<?> fieldType = field.getType();
        try {
            if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue().trim();
                if (StringUtils.isNotEmpty(cellValue)) {
                    field.set(entity, Integer.parseInt(cellValue));
                }
            } else if (String.class == fieldType) {
                if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        field.set(entity, dateFormat.format(date));
                    } else {
                        cell.setCellType(CellType.STRING);
                        field.set(entity, cell.getStringCellValue().trim());
                    }
                } else {
                    cell.setCellType(CellType.STRING);
                    field.set(entity, cell.getStringCellValue().trim());
                }
            } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue().trim();
                if (StringUtils.isNotEmpty(cellValue)) {
                    field.set(entity, Long.parseLong(cellValue));
                }
            } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue().trim();
                if (StringUtils.isNotEmpty(cellValue)) {
                    field.set(entity, Double.parseDouble(cellValue));
                }
            } else if (Date.class == fieldType) {
                field.set(entity, cell.getDateCellValue());
            } else if (BigDecimal.class == fieldType) {
                CellType cellType = cell.getCellTypeEnum();
                //校验数值位公式计算获得
                if (cell != null) {
                    if (Cell.CELL_TYPE_FORMULA == cell.getCellType()) {
                        String cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                        field.set(entity, new BigDecimal(cellValue));
                    } else if (CellType.NUMERIC.equals(cellType)) {
                        //获取导入的数字长度
                        Integer length = this.getNumberLength(cell);
                        //校验excel数值格式长度不能超过15位
                        if (length < 16) {
                            //精度丢失问题修改
                            String cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                            if (StringUtils.isNotEmpty(cellValue)) {
                                field.set(entity, new BigDecimal(cellValue));
                            }
                        } else {
                            throw new RuntimeException("解析excel数值失败; ");
                        }

                    } else  if (CellType.BLANK.equals(cellType)) {

                        field.set(entity, null);

                    } else {
                            throw new RuntimeException("列数据格式必须为数值类型; ");

                    }
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
            String errorSheetName = lineErrorMap.get("errorSheetName");
            if (StringUtils.isEmpty(errorSheetName)) {
                lineErrorMap.put("errorSheetName", sheetName);
            }
            String oldLineNum = lineErrorMap.get("lineNum");
            if (StringUtils.isEmpty(oldLineNum)) {
                //Modified By meibing.li@hand.china.com On 2018/12/01 : 以行号值为提示值
                //lineErrorMap.put("lineNum", String.valueOf(lineNum));
//                lineErrorMap.put("lineNum", cell.getStringCellValue());
                lineErrorMap.put("lineNum", String.valueOf(cell.getRow().getCell(0)));
            }
            String errorMessage = null;
            if (StringUtils.isNotEmpty(e.toString())) {
                Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                Matcher m = p.matcher(e.toString());
                if (m.find()) {
                    String reqResult = e.toString();
                    errorMessage = "\"" + titleName.substring(titleName.indexOf("->") + 2)
                            + reqResult.substring(reqResult.indexOf(":"));
                } else {
                    errorMessage = "\"" + titleName.substring(titleName.indexOf("->") + 2) + "\"列数据格式错误; ";
                }
            }
            String oldErrorMessage = lineErrorMap.get("errorMessage");
            String newErrorMessage = errorMessage;
            if (StringUtils.isNotEmpty(oldErrorMessage)) {
                newErrorMessage = oldErrorMessage + errorMessage;
            }
            lineErrorMap.put("errorMessage", newErrorMessage);
        }
        return entity;
    }

    /**
     * 获取导入的数字长度
     * @param cell 单元格对象
     * @return 返回值
     */
    private Integer getNumberLength(Cell cell) {
        Integer count = 0;
        if (null != cell) {
            DecimalFormat ds = new DecimalFormat();
            String cellNumber = ds.format(Double.parseDouble(cell.toString())).trim();
            Pattern p = Pattern.compile("[0-9]");
            Matcher m = p.matcher(cellNumber);
            while (m.find()) {
                count++;
            }
        }
        return count;
    }


    /**
     * 校验文件模板是否存在空行，去除空行且获取实际有效行数(通用方法2)
     * 适用于读取自定义的开始行
     *
     * @param sheet    sheet参数
     * @param colNum   所有列
     * @param rows     获取当前有效行数
     * @param startNum 获取当前起始行数
     * @return 行数
     * @throws Exception 异常处理
     */
    public static int getInviliadAllRows(Sheet sheet, int colNum, int rows, int startNum) throws Exception {
        for (int i = rows; i >= startNum; i--) {
            int col = 0;
            Row row = sheet.getRow(i);
            if (row != null) {
                while (col < colNum) {
                    Cell cell = row.getCell(col);
                    if (cell == null) {
                        ++col;
                        continue;
                    } else {
                        String value = formatCellValueToStringUtil(cell);
                        if (StringUtils.isEmpty(value)) {
                            ++col;
                            continue;
                        } else {
                            break;
                        }
                    }
                }
                if (col == colNum) {
                    //当空行存在有效行数据中间
                    if (i >= 0 && i < rows) {
                        sheet.shiftRows(i + 1, rows, -1);
                    }
                    //当空行存在于最后
                    if (i == rows) {
                        if (row != null) {
                            sheet.removeRow(row);
                        }
                    }
                    rows -= 1;
                }
            } else {
                if (i >= 0 && i < rows) {
                    sheet.shiftRows(i + 1, rows, -1);
                }
                rows -= 1;
                continue;
            }

        }
        return rows;
    }


    /**
     * 对Excel每列数据进行格式化(对于HSSF/XSSF)
     *
     * @param cell 当前单元格
     * @return s 返回格式化后数据
     */
    public static String formatCellValueToStringUtil(Cell cell) {
        String cellValue = null;
        DecimalFormat df1 = new DecimalFormat("0"); //整数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getRichStringCellValue().getString().trim();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) { //日期
                        cellValue = sdf.format(cell.getDateCellValue());
                    } else { //数值
                        Long longVal = Math.round(cell.getNumericCellValue()); //将其转换为整型
                        Double value = cell.getNumericCellValue();
                        if (Double.parseDouble(longVal + DOT_ZERO) == value) { //整型
                            cellValue = df1.format(cell.getNumericCellValue());
                        } else {
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = null;
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }

        return cellValue;
    }
}
