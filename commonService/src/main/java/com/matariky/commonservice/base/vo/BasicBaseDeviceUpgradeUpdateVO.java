package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class BasicBaseDeviceUpgradeUpdateVO {


    @ApiModelProperty(value = "升级包 Name")
    @NotBlank
    private String packageName;

    @ApiModelProperty(value = "升级包版本")
    @NotBlank
    private String packageVersion;

    @ApiModelProperty(value = " Remark ")
    @NotBlank
    private String remark;

    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;
}
