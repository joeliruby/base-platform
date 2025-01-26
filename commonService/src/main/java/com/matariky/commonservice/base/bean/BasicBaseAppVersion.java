package com.matariky.commonservice.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@TableName("basic_base_appversion")
public class BasicBaseAppVersion {

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "APP Name")
	private String appName;

	@ApiModelProperty(value = "Version Name")
	private String versionName;

	@ApiModelProperty(value = "Version Number")
	private String versionNo;

	@ApiModelProperty(value = "Client Type")
	private String upgradeType;

	@ApiModelProperty(value = "Version Content")
	private String versionContent;

	@ApiModelProperty(value = "Upgrade File")
	private String upgradeFile;

	@ApiModelProperty(value = "Download QR Code")
	private String downloadQrcode;

	@ApiModelProperty(value = "Whether Force Update (1 Yes, 0 No)")
	private Integer isForceUpdates;

	@ApiModelProperty(value = "Remark")
	private String remark;

	@ApiModelProperty(value = "Creation Time")
	private Long createTime;

	@ApiModelProperty(value = "Update Time")
	private Long updateTime;

	@ApiModelProperty(value = "Deletion Time")
	private Long deleteTime;

	@ApiModelProperty(value = "Creator ID")
	private Long createBy;

	@ApiModelProperty(value = "Updater ID")
	private Long updateBy;

	@ApiModelProperty(value = "Operator Department Code")
	private String operatorOrgCode;

	@ApiModelProperty(value = "Operator Personal Organization Code")
	private String operatorSelfOrgCode;

	@ApiModelProperty(value = "Tenant ID")
	private String tenantId;


}
