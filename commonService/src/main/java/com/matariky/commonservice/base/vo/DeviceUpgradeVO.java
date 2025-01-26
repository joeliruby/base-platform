package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeviceUpgradeVO {

    @ApiModelProperty(value = " Device ID")
    private List<Long> deviceIdList;

    @ApiModelProperty(value = "升级Type （immediate：立刻Update，scheduler：定时Update）")
    private String upgradeType;

    @ApiModelProperty(value = "升级 Time ")
    private Long upgradeTime;


}
