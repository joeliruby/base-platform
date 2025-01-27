package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class BasicBaseDevicePackageAddVO {

    @ApiModelProperty(value = " Device Type ID")
    @NotNull
    private Long typeId;

    @ApiModelProperty(value = "  Upgrade Package Name")
    @NotBlank
    @Size(max = 200)
    private String packageName;

    @ApiModelProperty(value = " Upgrade  Description ")
    @NotBlank
    @Size(max = 500)
    private String upgradeContent;

    @ApiModelProperty(value = "  Upgrade Package Version")
    @NotBlank
    @Size(max = 100)
    private String packageVersion;

}
