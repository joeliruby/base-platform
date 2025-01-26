package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class BasicBaseAppVersionListVO {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "APP Name")
    private String appName;

    @ApiModelProperty(value = "版本 Name")
    private String versionName;

    @ApiModelProperty(value = "版本号")
    private String versionNo;

    @ApiModelProperty(value = "客户端Type ")
    private String upgradeType;

    @ApiModelProperty(value = "版本内容")
    private String versionContent;

    @ApiModelProperty(value = "升级文件")
    private String upgradeFile;

    @ApiModelProperty(value = "Download QR Code ")
    private String downloadQrcode;

    @ApiModelProperty(value = " Wether 强制Update 1是 0否")
    private Integer isForceUpdates;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    @ApiModelProperty(value = "创建 Time ")
    private Long createTime;

    @ApiModelProperty(value = "Update Time ")
    private Long updateTime;

    @ApiModelProperty(value = "删除 Time ")
    private Long deleteTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "Update人ID")
    private Long updateBy;

    @ApiModelProperty(value = "访问产生者部门编码")
    private String operatorOrgCode;

    @ApiModelProperty(value = "访问产生者个人组织机构编码")
    private String operatorSelfOrgCode;

    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;

    @ApiModelProperty(value = "创建人")
    private String realName;
}
