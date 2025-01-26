package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BasicBaseDeviceTypeInfo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = " Device Type 编码")
    private String typeCode;

    @ApiModelProperty(value = " Device Type  Name")
    private String typeName;

    @ApiModelProperty(value = " Device 厂家")
    private String deviceFactory;

    @ApiModelProperty(value = " Device 型号")
    private String deviceModel;

    @ApiModelProperty(value = " Device 描述")
    private String deviceDescribe;

    @ApiModelProperty(value = "当前固件版本号")
    private String currentVersion;

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

    @ApiModelProperty(value = " Device Type key(来自 Data 字典)")
    private String typeKey;

    @ApiModelProperty(value = "升级 Time ")
    private String  upgradeTime;

    @ApiModelProperty(value = " Wether 自动升级")
    private String  isAutoUpgrade;

    @ApiModelProperty(value = " Status （0：停用，1：启用）")
    private Integer  status;

    @ApiModelProperty(value = "升级Type ")
    private String upgradeType;

    private String  protocolType;

    private List<BasicBaseDevicecommandVO> commandList;
}
