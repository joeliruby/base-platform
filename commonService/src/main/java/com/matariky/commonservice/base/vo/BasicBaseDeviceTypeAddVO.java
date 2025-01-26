package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BasicBaseDeviceTypeAddVO {

    @ApiModelProperty(value = " Device Type key(来自 Data 字典)")
    @NotBlank
    @Size(max = 20)
    private String typeKey;

    @ApiModelProperty(value = " Device 型号")
    @NotBlank
    @Size(max = 200)
    private String deviceModel;

    @ApiModelProperty(value = " Device 厂家")
    @NotBlank
    @Size(max = 200)
    private String deviceFactory;

    @ApiModelProperty(value = " Device 描述")
    @Size(max = 500)
    private String deviceDescribe;

    @ApiModelProperty(value = " Wether 自动升级")
    private String isAutoUpgrade;

    @ApiModelProperty(value = "协议Type ")
    private String protocolType;

}
