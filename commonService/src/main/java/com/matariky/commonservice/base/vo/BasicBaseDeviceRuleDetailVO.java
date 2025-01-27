package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class BasicBaseDeviceRuleDetailVO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = " Rule ID")
    private Long ruleId;

    @ApiModelProperty(value = " Filter Condition ")
    private String filterConditions;

    @ApiModelProperty(value = " Condition Setting ")
    private String conditionSetting;

    @ApiModelProperty(value = "Set Values")
    private String setValue;

    @ApiModelProperty(value = "Set Values Name")
    private String setValueName;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    @ApiModelProperty(value = "Create  Time ")
    private Long createTime;

    @ApiModelProperty(value = "Update Time ")
    private Long updateTime;

    @ApiModelProperty(value = "Delete  Time ")
    private Long deleteTime;

    @ApiModelProperty(value = "Create  User ID")
    private Long createBy;

    @ApiModelProperty(value = "Update User ID")
    private Long updateBy;

    @ApiModelProperty(value = "Vistiting Operator Department Code ")
    private String operatorOrgCode;

    @ApiModelProperty(value = "Vistitor Organization Code Code ")
    private String operatorSelfOrgCode;

    @ApiModelProperty(value = " Real Name ")
    private String realName;
}
