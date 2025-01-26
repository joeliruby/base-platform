package com.matariky.commonservice.base.vo;

import lombok.Data;

@Data
public class GoodsBatchInfoVO {

    private Long id;

    private Long goodsId;

    private String goodsName;

    private String goodsCode;

    private String goodsImage;

    private String tid;

    private String epc;

    private Long createTime;


}
