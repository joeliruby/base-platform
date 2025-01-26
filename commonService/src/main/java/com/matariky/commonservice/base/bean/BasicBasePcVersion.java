package com.matariky.commonservice.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.String;
import java.lang.Long;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
@TableName("basic_base_pcversion")
public class BasicBasePcVersion {

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Version Name")
	private String versionName;

	@ApiModelProperty(value = "Version Number")
	private String versionNo;

	@ApiModelProperty(value = "Version Content")
	private String versionContent;

	@ApiModelProperty(value = "Requirement Collection Date")
	private Long requirementDate;

	@ApiModelProperty(value = "Message Auto Shutdown Time")
	private String messageShutdownTime;

	@ApiModelProperty(value = "Remark")
	private String remark;

	@ApiModelProperty(value = "Creation Time")
	private Long createTime;

	@ApiModelProperty(value = "Update Time")
	private Long updateTime;

	@ApiModelProperty(value = "Deletion Time")
	private Long deleteTime;

	@ApiModelProperty(value = "Creator User ID")
	private Long createBy;

	@ApiModelProperty(value = "Updater User ID")
	private Long updateBy;

	@ApiModelProperty(value = "Operator Department Code")
	private String operatorOrgCode;

	@ApiModelProperty(value = "Operator Personal Organization Code")
	private String operatorSelfOrgCode;

	@ApiModelProperty(value = "Tenant ID")
	private String tenantId;



}
