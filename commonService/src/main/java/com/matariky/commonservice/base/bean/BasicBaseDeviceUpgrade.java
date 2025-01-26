package com.matariky.commonservice.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
@TableName("basic_base_deviceupgrade")
public class BasicBaseDeviceUpgrade {


	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Device ID")
	private Long deviceId;

	@ApiModelProperty(value = "Device Firmware Package ID")
	private Long packageId;

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

	@ApiModelProperty(value = "Upgrade Status (0 Not Executed, 1 Executed)")
	private Integer upgradeStatus;

	private String upgradeType;

	private Long upgradeTime;

	private Long executeTime;

	private Long rowId;

}
