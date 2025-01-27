package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BasicBaseCodingRulesUpdateStatusVO {

    @NotNull
    private Long id;

    @ApiModelProperty(value = " Status 0: Deactivate,1: Activate")
    @NotNull
    private Integer status;

}
