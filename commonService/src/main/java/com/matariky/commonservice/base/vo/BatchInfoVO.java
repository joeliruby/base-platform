package com.matariky.commonservice.base.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BatchInfoVO {

    @NotNull
    private Long goodsId;

    private Long  createTime;

    private Long  createTimeEnd;

    private String epc;

    private String tid;
}
