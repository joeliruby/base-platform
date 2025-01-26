package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class BasicBaseAppVersionUpdateVO {


    @ApiModelProperty(value = "版本 Name")
    @NotBlank
    @Size(max = 200)
    private String versionName;

    @ApiModelProperty(value = "版本号")
    @NotBlank
    @Size(max = 200)
    private String versionNo;

    @ApiModelProperty(value = "客户端Type ")
    @NotBlank
    @Size(max = 100)
    private String upgradeType;

    @ApiModelProperty(value = "版本内容")
    @NotBlank
    @Size(max = 500)
    private String versionContent;

    @ApiModelProperty(value = " Wether 强制Update 1是 0否")
    @NotNull
    private Integer isForceUpdates;


    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;

}
