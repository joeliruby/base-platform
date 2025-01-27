package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceTypeOption {

    @ApiModelProperty(value = "id")
    private Long typeId;

    @ApiModelProperty(value = " Device Type  Code ")
    private String typeCode;

    @ApiModelProperty(value = " Device Type  Name")
    private String typeName;

    @ApiModelProperty(value = " Device  Factory  ")
    private String deviceFactory;

    @ApiModelProperty(value = " Device  Type ")
    private String deviceModel;

    @ApiModelProperty(value = " Text ")
    private String label;

}
