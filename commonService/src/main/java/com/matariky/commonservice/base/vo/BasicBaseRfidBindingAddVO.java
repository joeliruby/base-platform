package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBaseRfidBindingAddVO {


    @ApiModelProperty(value = " Item ID")
    @NotNull
    private Long goodsId;

    @ApiModelProperty(value = " Device ID")
    @NotNull
    private Long deviceId;

    @ApiModelProperty(value = "EPC")
    @NotBlank
    @Size(max = 100)
    private String epc;

    @ApiModelProperty(value = "TID")
    @NotBlank
    @Size(max = 100)
    private String tid;

    @ApiModelProperty(value = " Label  Code ")
    private Long tagCode;
}
