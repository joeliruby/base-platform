package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeviceUpgradeVO {

    @ApiModelProperty(value = " Device ID")
    private List<Long> deviceIdList;

    @ApiModelProperty(value = " Upgrade Type immediate: Immediate Update,scheduler: Scheduled Update")
    private String upgradeType;

    @ApiModelProperty(value = " Upgrade  Time ")
    private Long upgradeTime;

}
