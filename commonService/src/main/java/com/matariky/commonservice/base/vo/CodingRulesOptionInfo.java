package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CodingRulesOptionInfo {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = " Rule  Name")
    private String rulesName;

    @ApiModelProperty(value = "Display Text ")
    private String label;
}
