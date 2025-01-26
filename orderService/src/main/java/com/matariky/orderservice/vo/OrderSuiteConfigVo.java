package com.matariky.orderservice.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderSuiteConfigVo {

    private Long numberStart;
    private Long numberEnd;
    private BigDecimal yearPrice;
    private BigDecimal averagePrice;
    private String suiteConfigCode;

}
