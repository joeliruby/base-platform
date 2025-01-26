package com.matariky.commonservice.log.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseLogLoginInfo {


    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "'访问终端(PC端、手持机、 Print 机)")
    private String accessTerminal;
    @ApiModelProperty(value = "'用户名'")
    private String userName;
    @ApiModelProperty(value = "'外网IP'")
    private String outIp;
    @ApiModelProperty(value = "内网IP")
    private String inIp;
    @ApiModelProperty(value = "登录地址")
    private String loginAddress;
    @ApiModelProperty(value = "浏览器")
    private String  browser;
    @ApiModelProperty(value = "操作系统")
    private String  operatingSystem;
    @ApiModelProperty(value = "操作 Status ")
    private String  operateStatus;
    @ApiModelProperty(value = "访问日期")
    private  Long  accessTime;
    @ApiModelProperty(value = " Remark ")
    private String  remark;
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
    @ApiModelProperty(value = " Tenant  Name")
    private String  tenantName;
}
