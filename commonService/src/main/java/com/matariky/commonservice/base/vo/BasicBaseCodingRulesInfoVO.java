package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseCodingRulesInfoVO {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = " Rule  Name")
    private String rulesName;
    @ApiModelProperty(value = " Code  length ")
    private Integer codingLength;
    @ApiModelProperty(value = "  Unique The first character at the beginning of the code is E1 The second is E2")
    private String uniqueCode;
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
    @ApiModelProperty(value = "Visiting the Sectional Department Code ")
    private String operatorOrgCode;
    @ApiModelProperty(value = "Visit the generator personal organization Code ")
    private String operatorSelfOrgCode;
    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;
    @ApiModelProperty(value = "Creater")
    private String realName;
    @ApiModelProperty(value = " Status （0： Deactivate  ,1： Activate）")
    private Integer status;
    @ApiModelProperty(value = " Rule Type   , From  Data   Dictionary ")
    private Integer rulesType;
}
