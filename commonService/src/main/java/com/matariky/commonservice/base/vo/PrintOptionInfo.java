package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PrintOptionInfo {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = " Device Type  Name")
    private String typeName;

    @ApiModelProperty(value = " Device 编号")
    private String deviceCode;

    @ApiModelProperty(value = "显示文本")
    private String label;
}
