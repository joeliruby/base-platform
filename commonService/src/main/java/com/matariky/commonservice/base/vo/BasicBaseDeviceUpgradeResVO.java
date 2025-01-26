package com.matariky.commonservice.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BasicBaseDeviceUpgradeResVO {

    @JsonIgnore
    private String deviceIdListStr;

    @ApiModelProperty(value = " Device 固件包ID")
    private Long packageId;

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

    @ApiModelProperty(value = "升级 Status  0未执行 1已执行")
    private Integer upgradeStatus;

    private String upgradeType;

    private Long upgradeTime;

    private Long executeTime;

    private List<DeviceIdVO> deviceIdList;

    private Long rowId;
}
