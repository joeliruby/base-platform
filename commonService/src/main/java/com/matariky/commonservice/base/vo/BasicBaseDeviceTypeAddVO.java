package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BasicBaseDeviceTypeAddVO {

    @ApiModelProperty(value = " Device Type key( From  Data   Dictionary )")
    @NotBlank
    @Size(max = 20)
    private String typeKey;

    @ApiModelProperty(value = " Device  Type ")
    @NotBlank
    @Size(max = 200)
    private String deviceModel;

    @ApiModelProperty(value = " Device  Factory  ")
    @NotBlank
    @Size(max = 200)
    private String deviceFactory;

    @ApiModelProperty(value = " Device  Description ")
    @Size(max = 500)
    private String deviceDescribe;

    @ApiModelProperty(value = " Wether  Automatic Upgrade ")
    private String isAutoUpgrade;

    @ApiModelProperty(value = "  Protocol Type ")
    private String protocolType;

}
