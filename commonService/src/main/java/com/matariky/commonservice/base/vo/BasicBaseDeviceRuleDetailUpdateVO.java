package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BasicBaseDeviceRuleDetailUpdateVO {

    @ApiModelProperty(value = " Filter Condition ")
    @NotBlank
    @Size(max = 100)
    private String filterConditions;

    @ApiModelProperty(value = " Condition Setting ")
    @NotBlank
    @Size(max = 100)
    private String conditionSetting;

    @ApiModelProperty(value = "Set Values")
    @NotBlank
    @Size(max = 100)
    private String setValue;

}
