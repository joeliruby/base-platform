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
@TableName("basic_base_devicetype")
public class BasicBaseDeviceType {

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Device Type Code")
	private String typeCode;

	@ApiModelProperty(value = "Device Type Name")
	private String typeName;

	@ApiModelProperty(value = "Device Manufacturer")
	private String deviceFactory;

	@ApiModelProperty(value = "Device Model")
	private String deviceModel;

	@ApiModelProperty(value = "Device Description")
	private String deviceDescribe;

	@ApiModelProperty(value = "Current Firmware Version")
	private String currentVersion;

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

	@ApiModelProperty(value = "Device Type Key (from Data Dictionary)")
	private String typeKey;

	@ApiModelProperty(value = "Whether Auto Upgrade")
	private String isAutoUpgrade;

	@ApiModelProperty(value = "Status (0: Disabled, 1: Enabled)")
	private Integer status;

	private String protocolType;

}
