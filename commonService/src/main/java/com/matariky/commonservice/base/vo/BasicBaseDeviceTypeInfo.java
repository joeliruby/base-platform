package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BasicBaseDeviceTypeInfo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = " Device Type  Code ")
    private String typeCode;

    @ApiModelProperty(value = " Device Type  Name")
    private String typeName;

    @ApiModelProperty(value = " Device  Factory  ")
    private String deviceFactory;

    @ApiModelProperty(value = " Device  Type ")
    private String deviceModel;

    @ApiModelProperty(value = " Device  Description ")
    private String deviceDescribe;

    @ApiModelProperty(value = "  Current  Firmware  Version Number")
    private String currentVersion;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    @ApiModelProperty(value = "Create  Time ")
    private Long createTime;

    @ApiModelProperty(value = "Update Time ")
    private Long updateTime;

    @ApiModelProperty(value = "Delete  Time ")
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

    @ApiModelProperty(value = " Device Type key( From  Data   Dictionary )")
    private String typeKey;

    @ApiModelProperty(value = " Upgrade  Time ")
    private String upgradeTime;

    @ApiModelProperty(value = " Wether  Automatic Upgrade ")
    private String isAutoUpgrade;

    @ApiModelProperty(value = " Status （0： Deactivate  ,1： Activate）")
    private Integer status;

    @ApiModelProperty(value = " Upgrade Type ")
    private String upgradeType;

    private String protocolType;

    private List<BasicBaseDevicecommandVO> commandList;
}
