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


    @ApiModelProperty(value = " Device  Upgrade  Pagination ")
    private List<DeviceUpgradeVO> deviceUpgradeList;

    @ApiModelProperty(value = " Device  Firmware ID")
    @NotNull
    private Long packageId;

}
