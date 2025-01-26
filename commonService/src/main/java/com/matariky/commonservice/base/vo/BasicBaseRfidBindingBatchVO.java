package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BasicBaseRfidBindingBatchVO {

    @ApiModelProperty(value = "EPC")
    @NotBlank
    @Size(max = 100)
    private String epc;

    @ApiModelProperty(value = "TID")
    @NotBlank
    @Size(max = 100)
    private String tid;

}
