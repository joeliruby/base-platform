package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class BasicBaseDeviceUpgradeAddDTO {


    @ApiModelProperty(value = " Device 升级列表")
    private List<DeviceUpgradeVO> deviceUpgradeList;

    @ApiModelProperty(value = " Device 固件包ID")
    @NotNull
    private Long packageId;

}
