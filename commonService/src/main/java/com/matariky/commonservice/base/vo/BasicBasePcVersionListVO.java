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
    @ApiModelProperty(value = " Version Name")
    private String versionName;

    @ApiModelProperty(value = " Version Number")
    private String versionNo;

    @ApiModelProperty(value = "  Version Content ")
    private String versionContent;

    @ApiModelProperty(value = " Requirement  Collection Date ")
    private Long requirementDate;

    @ApiModelProperty(value = "  Message  Alert  Automatic Close  Time ")
    private String messageShutdownTime;

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

    @ApiModelProperty(value = "Creater")
    private String realName;
}
