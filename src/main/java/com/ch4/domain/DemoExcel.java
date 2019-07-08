/**
 * 文件名: DemoExcel.java
 * 版权：Copyright 2017-2022 HAND All Rights Reserved.
 * 描述:
 */
package com.ch4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description:
 *
 * @author: Shenshuaihu
 * @version: 1.0
 * @data: 2019-07-03 14:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoExcel {

    private Long id;
    private Long lineNum;

    /**
     * 流水号
     */
    private String bankSeqNumber;

    /**
     * 流水金额
     */
    private BigDecimal statementAmount;

    /**
     * 本次核销金额
     */
    private BigDecimal amount;
}
