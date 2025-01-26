package com.matariky.orderservice.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderInfoAddVo {
    private String orderCode;
    private String orderShowCode;
    private String contractCode;
    private String tenantId;
    private String orderTenantId;
    private String tenantShowId;
    private String suiteCode;
    private String contacts;
    private String contactsPhone;
    private String expirationStartTime;
    private String expirationEndTime;
    private Integer accountNumber;
    private String chargeMode;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String paymentTime;
    private String paymentVoucherPath;
    private String remark;
}
