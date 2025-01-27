package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBaseAppVersionUpdateVO {

    @ApiModelProperty(value = " Version Name")
    @NotBlank
    @Size(max = 200)
    private String versionName;

    @ApiModelProperty(value = " Version Number")
    @NotBlank
    @Size(max = 200)
    private String versionNo;

    @ApiModelProperty(value = "Client Type ")
    @NotBlank
    @Size(max = 100)
    private String upgradeType;

    @ApiModelProperty(value = "  Version Content ")
    @NotBlank
    @Size(max = 500)
    private String versionContent;

    @ApiModelProperty(value = " Wether Force Update 1yes 0 no")
    @NotNull
    private Integer isForceUpdates;

    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;

}
