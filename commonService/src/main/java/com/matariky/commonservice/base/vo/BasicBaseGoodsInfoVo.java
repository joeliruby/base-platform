package com.matariky.commonservice.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseGoodsInfoVo {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "货物编码")
    private String goodsCode;
    @ApiModelProperty(value = "货物 Name")
    private String goodsName;
    @ApiModelProperty(value = "货物图片")
    private String goodsImage;
    @ApiModelProperty(value = " Item 描述")
    private String goodsDescribe;
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

    private Boolean isUsedByOther=false;

    @JsonIgnore
    private Long a;
    @JsonIgnore
    private Long b;
    @JsonIgnore
    private Long c;
    @JsonIgnore
    private Long d;
    @JsonIgnore
    private Long e;

}
