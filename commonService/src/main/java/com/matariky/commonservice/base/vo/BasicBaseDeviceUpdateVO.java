package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBaseDeviceUpdateVO {

    @ApiModelProperty(value = " Device Type ID")
    @NotNull
    private Long typeId;

    @ApiModelProperty(value = " Device   Code ")
    @NotBlank
    @Size(max = 200)
    private String deviceCode;

    @ApiModelProperty(value = " Device  Description ")
    @Size(max = 500)
    private String deviceDescribe;

    @ApiModelProperty(value = " Device  Power ")
    @Size(max = 100)
    private String deviceDbm;

    @ApiModelProperty(value = " Device IP")
    @Size(max = 50)
    private String deviceIp;

    @ApiModelProperty(value = " Device MAC Address ")
    @Size(max = 200)
    private String deviceMac;

    @ApiModelProperty(value = "gis Address ")
    @Size(max = 100)
    private String gisAddress;

    @ApiModelProperty(value = "gis Address  Name")
    @Size(max = 200)
    private String gisAddressName;

    @ApiModelProperty(value = " Install  Address ")
    @Size(max = 200)
    private String installAddress;

    @ApiModelProperty(value = " Install  Image ")
    @Size(max = 200)
    private String installImg;

    @ApiModelProperty(value = "ID")
    @NotNull
    private Long id;
}
