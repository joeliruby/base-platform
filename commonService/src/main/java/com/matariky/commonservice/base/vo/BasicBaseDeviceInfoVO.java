package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseDeviceInfoVO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = " Device Type ID")
    private Long typeId;

    @ApiModelProperty(value = " Device   Code ")
    private String deviceCode;

    @ApiModelProperty(value = " Device  Description ")
    private String deviceDescribe;

    @ApiModelProperty(value = " Device  Power ")
    private String deviceDbm;

    @ApiModelProperty(value = " Device IP")
    private String deviceIp;

    @ApiModelProperty(value = " Device MAC Address ")
    private String deviceMac;

    @ApiModelProperty(value = "  longitude ")
    private String longitude;

    @ApiModelProperty(value = "  latitude ")
    private String latitude;

    @ApiModelProperty(value = " Install  Address ")
    private String installAddress;

    @ApiModelProperty(value = " Install  Image ")
    private String installImg;

    @ApiModelProperty(value = "  Previous  Version Number")
    private String prevVersion;

    @ApiModelProperty(value = "  Current  Version Number")
    private String currentVersion;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    @ApiModelProperty(value = "Create  Time ")
    private Long createTime;

    @ApiModelProperty(value = "Update Time ")
    private Long updateTime;

    @ApiModelProperty(value = "Update Time ")
    private Long deleteTime;

    @ApiModelProperty(value = "Create  User ID")
    private Long createBy;

    @ApiModelProperty(value = "Update User ID")
    private Long updateBy;

    @ApiModelProperty(value = "Vistiting Operator Department Code ")
    private String operatorOrgCode;

    @ApiModelProperty(value = "Vistitor Organization Code Code ")
    private String operatorSelfOrgCode;

    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;

    @ApiModelProperty(value = " Device Type  Name")
    private String typeName;

    @ApiModelProperty(value = " Device Type  Code ")
    private String typeCode;

    @ApiModelProperty(value = " Device  Type ")
    private String deviceModel;

    @ApiModelProperty(value = " Device  Factory  ")
    private String deviceFactory;

    @ApiModelProperty(value = " Real Name")
    private String realName;

    @ApiModelProperty(value = "  Online Status (off= Offline  ,on=  Online)")
    private String status;

    @ApiModelProperty(value = "gis Address  Name")
    private String gisAddressName;
}
