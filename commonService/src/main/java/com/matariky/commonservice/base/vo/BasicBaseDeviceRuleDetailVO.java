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

    @ApiModelProperty(value = "过滤条件")
    private String filterConditions;

    @ApiModelProperty(value = "条件设定")
    private String conditionSetting;

    @ApiModelProperty(value = "设定值")
    private String setValue;

    @ApiModelProperty(value = "设定值 Name")
    private  String  setValueName;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    @ApiModelProperty(value = "创建 Time ")
    private Long createTime;

    @ApiModelProperty(value = "Update Time ")
    private Long updateTime;

    @ApiModelProperty(value = "删除 Time ")
    private Long deleteTime;

    @ApiModelProperty(value = "创建用户ID")
    private Long createBy;

    @ApiModelProperty(value = "Update用户ID")
    private Long updateBy;

    @ApiModelProperty(value = "访问产生者部门编码")
    private String operatorOrgCode;

    @ApiModelProperty(value = "访问产生者个人组织机构编码")
    private String operatorSelfOrgCode;

    @ApiModelProperty(value = "真实姓名")
    private String realName;
}
