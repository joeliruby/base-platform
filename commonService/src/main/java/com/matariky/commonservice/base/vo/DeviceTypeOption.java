package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceTypeOption {

    @ApiModelProperty(value = "id")
    private Long typeId;

    @ApiModelProperty(value = " Device Type 编码")
    private String typeCode;

    @ApiModelProperty(value = " Device Type  Name")
    private String typeName;

    @ApiModelProperty(value = " Device 厂家")
    private String deviceFactory;

    @ApiModelProperty(value = " Device 型号")
    private String deviceModel;

    @ApiModelProperty(value = "文本")
    private String  label;

}
