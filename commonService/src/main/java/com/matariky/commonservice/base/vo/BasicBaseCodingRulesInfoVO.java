package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseCodingRulesInfoVO {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = " Rule  Name")
    private String rulesName;
    @ApiModelProperty(value = "编码长度")
    private Integer codingLength;
    @ApiModelProperty(value = "唯一码开头字符 第一条为E1 第二条为E2")
    private String uniqueCode;
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
    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;
    @ApiModelProperty(value = "创建人")
    private String realName;
    @ApiModelProperty(value = " Status （0：停用，1：启用）")
    private Integer status;
    @ApiModelProperty(value = " Rule Type ，来自 Data 字典")
    private Integer rulesType;
}
