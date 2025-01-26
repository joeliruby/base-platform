package com.matariky.commonservice.base.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasicBaseGoodsBatchResVO {

    private Long id;
    private Long goodsId;
    private String batchCode;
    private Long productionDate;
    private Long validityDate;
    private String supplier;
    private BigDecimal amount;
    private String remark;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
    private Long createBy;
    private Long updateBy;
    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String tenantId;
    private String goodsName;
    private String goodsCode;
    private String goodsImage;
    private String realName;
}
