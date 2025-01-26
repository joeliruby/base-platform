package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BasicBaseDeviceRuleAddVO {

    @ApiModelProperty(value = " Device Type ID")
    @NotNull
    private Long typeId;

}
