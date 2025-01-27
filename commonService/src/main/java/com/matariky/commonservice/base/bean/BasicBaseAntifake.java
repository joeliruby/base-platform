package com.matariky.commonservice.base.bean;

import com.matariky.model.QueryDataIsolation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseAntifake extends QueryDataIsolation {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Validation Batch Number")
	private String validateBatchCode;

	@ApiModelProperty(value = "IP Address")
	private String ipAddress;

	@ApiModelProperty(value = "Validation URL")
	private String validateUrl;

	@ApiModelProperty(value = "Validation Quantity")
	private Integer validateNum;

	@ApiModelProperty(value = "Success Quantity")
	private Integer successNum;

	@ApiModelProperty(value = "Failed Quantity")
	private Integer failNum;

	@ApiModelProperty(value = "Validation Status, 1 All Passed, 2 Partially Passed, 3 All Failed, 4 No Label")
	private Integer validateStatus;

	@ApiModelProperty(value = "Validation Time")
	private Long validateTiime;

	private String remark;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;

	@ApiModelProperty(value = "Validation Start Date")
	private String validationTimeStart;

	@ApiModelProperty(value = "Validation End Date")
	private String validationTimeEnd;

	@ApiModelProperty(value = "Real Name")
	private String realName;

}
