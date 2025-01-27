package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PrintOptionInfo {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = " Device Type  Name")
    private String typeName;

    @ApiModelProperty(value = " Device   Code ")
    private String deviceCode;

    @ApiModelProperty(value = "Display Text ")
    private String label;
}
