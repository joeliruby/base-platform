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
@TableName("basic_base_rfid_binding")
public class BasicBaseRfidBinding {

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Label ID")
	private Long rfidId;

	@ApiModelProperty(value = "Item ID")
	private Long goodsId;

	@ApiModelProperty(value = "Device ID")
	private Long deviceId;

	@ApiModelProperty(value = "EPC")
	private String epc;

	@ApiModelProperty(value = "TID")
	private String tid;

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

	@ApiModelProperty(value = "Label Code")
	private Long tagCode;

}
