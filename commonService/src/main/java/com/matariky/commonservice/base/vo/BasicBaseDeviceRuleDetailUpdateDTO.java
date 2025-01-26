package com.matariky.commonservice.base.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBaseDeviceRuleDetailUpdateDTO {

    @ApiModelProperty(value = "过滤条件")
    @NotBlank
    @Size(max = 100)
    private String filterConditions;

    @ApiModelProperty(value = "条件设定")
    @NotBlank
    @Size(max = 100)
    private String conditionSetting;

    @ApiModelProperty(value = "设定值")
    @NotBlank
    @Size(max = 100)
    private String setValue;

    @ApiModelProperty(value = " Rule  DetailID")
    @NotNull
    private Long id;

}
