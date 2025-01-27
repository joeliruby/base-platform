package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseAppVersionListVO {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "APP Name")
    private String appName;

    @ApiModelProperty(value = " Version Name")
    private String versionName;

    @ApiModelProperty(value = " Version Number")
    private String versionNo;

    @ApiModelProperty(value = "Client Type ")
    private String upgradeType;

    @ApiModelProperty(value = " Version content")
    private String versionContent;

    @ApiModelProperty(value = " Upgrade document")
    private String upgradeFile;

    @ApiModelProperty(value = "Download QR Code ")
    private String downloadQrcode;

    @ApiModelProperty(value = " Wether Forced Update 1 yes 0 no")
    private Integer isForceUpdates;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    @ApiModelProperty(value = "Create  Time ")
    private Long createTime;

    @ApiModelProperty(value = "Update Time ")
    private Long updateTime;

    @ApiModelProperty(value = "Delete  Time ")
    private Long deleteTime;

    @ApiModelProperty(value = "Creater ID")
    private Long createBy;

    @ApiModelProperty(value = "Updater ID")
    private Long updateBy;

    @ApiModelProperty(value = "Visiting the Sectional Department Code ")
    private String operatorOrgCode;

    @ApiModelProperty(value = "Visit the generator personal organization Code ")
    private String operatorSelfOrgCode;

    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;

    @ApiModelProperty(value = "Creater")
    private String realName;
}
