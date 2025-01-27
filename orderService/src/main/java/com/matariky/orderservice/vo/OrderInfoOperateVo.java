package com.matariky.orderservice.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfoOperateVo {
    private String orderId;

    private Long recordType;
    private String expirationEndTime;
    private Integer accountNumber;
    private String chargeMode;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String paymentTime;
    private String paymentVoucherPath;
    private String remark;
    private String orderStatus;
    private boolean isRecord;
}
