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
    @ApiModelProperty(value = " Rule Type ")
    private Integer rulesType;
    @ApiModelProperty(value = " Status ")
    private Integer status;
    @ApiModelProperty(value = " Rule 编码")
    private String rulesCode;
}
