package com.matariky.commonservice.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseGoodsInfoVo {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = " Goods  Code ")
    private String goodsCode;
    @ApiModelProperty(value = " Goods  Name")
    private String goodsName;
    @ApiModelProperty(value = " Goods  Image ")
    private String goodsImage;
    @ApiModelProperty(value = " Item   Description ")
    private String goodsDescribe;
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
    @ApiModelProperty(value = "Visitor Organization Code Code ")
    private String operatorOrgCode;
    @ApiModelProperty(value = "Visitor Self Organization Code Code ")
    private String operatorSelfOrgCode;
    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;
    @ApiModelProperty(value = "Creater")
    private String realName;

    private Boolean isUsedByOther = false;

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
