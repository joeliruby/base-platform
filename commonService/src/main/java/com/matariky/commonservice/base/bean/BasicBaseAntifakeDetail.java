package com.matariky.commonservice.base.bean;

import com.matariky.model.QueryDataIsolation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseAntifakeDetail extends QueryDataIsolation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Detail ID")
	private Long id;

	@ApiModelProperty(value = "Main Table ID")
	private Long antifakeId;

	@ApiModelProperty(value = "Device ID")
	private Long deviceId;

	@ApiModelProperty(value = "Device Code")
	private String deviceCode;

	@ApiModelProperty(value = "Validation Result")
	private String validateResult;

	@ApiModelProperty(value = "Item ID")
	private Long goodsId;

	@ApiModelProperty(value = "Item Code")
	private String goodsCode;

	@ApiModelProperty(value = "Item Name")
	private String goodsName;

	@ApiModelProperty(value = "Item Image")
	private String goodsImage;

	@ApiModelProperty(value = "Item Description")
	private String goodsDescribe;

	@ApiModelProperty(value = "TID")
	private String tid;

	private String remark;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String validateBatchCode;
	private String ipAddress;
	private String validateUrl;
	private Long validateTiime;

	@ApiModelProperty(value = "Validation Start Date")
	private String validationTimeStart;

	@ApiModelProperty(value = "Validation End Date")
	private String validationTimeEnd;

	@ApiModelProperty(value = "Real Name")
	private String realName;

	private String tenantId;



}
