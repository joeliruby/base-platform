package com.matariky.commonservice.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("basic_base_device")
public class BasicBaseDevice {

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Device Type ID")
	private Long typeId;

	@ApiModelProperty(value = "Device Code")
	private String deviceCode;

	@ApiModelProperty(value = "Device Description")
	private String deviceDescribe;

	@ApiModelProperty(value = "Device Power")
	private String deviceDbm;

	@ApiModelProperty(value = "Device IP")
	private String deviceIp;

	@ApiModelProperty(value = "Device MAC Address")
	private String deviceMac;

	@ApiModelProperty(value = "Longitude")
	private String longitude;

	@ApiModelProperty(value = "Latitude")
	private String latitude;

	@ApiModelProperty(value = "GIS Address Name")
	private String gisAddressName;

	@ApiModelProperty(value = "Installation Address")
	private String installAddress;

	@ApiModelProperty(value = "Installation Image")
	private String installImg;

	@ApiModelProperty(value = "Previous Version Number")
	private String prevVersion;

	@ApiModelProperty(value = "Current Version Number")
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

}
