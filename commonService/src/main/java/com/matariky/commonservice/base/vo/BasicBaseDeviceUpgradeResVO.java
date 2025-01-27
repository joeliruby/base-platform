package com.matariky.commonservice.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BasicBaseDeviceUpgradeResVO {

    @JsonIgnore
    private String deviceIdListStr;

    @ApiModelProperty(value = " Device  Firmware ID")
    private Long packageId;

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

    @ApiModelProperty(value = " Upgrade  Status  0未执行 1已执行")
    private Integer upgradeStatus;

    private String upgradeType;

    private Long upgradeTime;

    private Long executeTime;

    private List<DeviceIdVO> deviceIdList;

    private Long rowId;
}
