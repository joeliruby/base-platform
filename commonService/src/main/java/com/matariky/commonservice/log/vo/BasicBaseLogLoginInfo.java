package com.matariky.commonservice.log.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseLogLoginInfo {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "' Access Terminal ( PC 、 Handheld Device 、 Printer)")
    private String accessTerminal;
    @ApiModelProperty(value = "' User  Name '")
    private String userName;
    @ApiModelProperty(value = "' Public Network IP'")
    private String outIp;
    @ApiModelProperty(value = " Internal Network IP")
    private String inIp;
    @ApiModelProperty(value = " Login  Address ")
    private String loginAddress;
    @ApiModelProperty(value = " Browser ")
    private String browser;
    @ApiModelProperty(value = "  Operation System")
    private String operatingSystem;
    @ApiModelProperty(value = " Operation Status ")
    private String operateStatus;
    @ApiModelProperty(value = " Access Date ")
    private Long accessTime;
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
    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;
    @ApiModelProperty(value = " Tenant  Name")
    private String tenantName;
}
