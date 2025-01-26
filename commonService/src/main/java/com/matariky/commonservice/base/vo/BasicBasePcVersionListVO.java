package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class BasicBasePcVersionListVO {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "版本 Name")
    private String versionName;

    @ApiModelProperty(value = "版本号")
    private String versionNo;

    @ApiModelProperty(value = "版本内容")
    private String versionContent;

    @ApiModelProperty(value = " Requirement 采集日期")
    private Long requirementDate;

    @ApiModelProperty(value = "消息提醒自动关闭 Time ")
    private String messageShutdownTime;

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
    private String  realName;
}
