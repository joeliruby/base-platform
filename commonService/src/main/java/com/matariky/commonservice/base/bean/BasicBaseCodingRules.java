package com.matariky.commonservice.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@TableName("basic_base_codingrules")
public class BasicBaseCodingRules {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = " Rule  Name")
    private String rulesName;
    @ApiModelProperty(value = "Coding length")
    private Integer codingLength;
    @ApiModelProperty(value = "The first character beginning of the only code is E1, E2, E2")
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
    @ApiModelProperty(value = "Code of the visitor department")
    private String operatorOrgCode;
    @ApiModelProperty(value = "Code of personal organizations in the visitors")
    private String operatorSelfOrgCode;
    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;
    @ApiModelProperty(value = " Rule Type ")
    private Integer rulesType;
    @ApiModelProperty(value = " Status ")
    private Integer status;
    @ApiModelProperty(value = " Rule  Code ")
    private String rulesCode;
}
