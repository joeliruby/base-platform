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
@TableName("basic_base_devicerule_detail")
public class BasicBaseDeviceRuleDetail {

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Rule ID")
	private Long ruleId;

	@ApiModelProperty(value = "Filter Conditions")
	private String filterConditions;

	@ApiModelProperty(value = "Condition Setting")
	private String conditionSetting;

	@ApiModelProperty(value = "Set Value")
	private String setValue;

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

}
