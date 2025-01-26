package com.matariky.orderservice.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfoPageVo {

    private Long id;
    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String orderCode;
    private String orderShowCode;
    private String contractCode;
    private String tenantId;
    private String orderTenantId;
    private String suiteCode;
    private String suiteConfigCode;
    private String contacts;
    private String contactsPhone;
    private Long expirationStartTime;
    private Long expirationEndTime;
    private Integer accountNumber;
    private String chargeMode;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Long paymentTime;
    private String paymentVoucherPath;
    private String orderStatus;
    private String remark;
    private Long createdBy;
    private Long createTime;
    private Long updateBy;
    private Long updateTime;
    private Long deleteTime;



    private String tenantName;
    private String suiteName;
    private String suiteNotes;
    private String realName;


    private Long alreadyAccountNumber;
    private Long renewalCount;
    private Long delayCount;
    private Long expansionCount;


    private boolean isDelete;
    private boolean isRenewal;
    private boolean isDelay;
    private boolean isExpansion;
    private boolean isAborted;

    private boolean isExpiringSoon;
}
