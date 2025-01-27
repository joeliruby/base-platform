package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBaseAppVersionAddVO {

    @ApiModelProperty(value = " Version Name")
    @NotBlank
    @Size(max = 200)
    private String versionName;

    @ApiModelProperty(value = " Version Number")
    @NotBlank
    @Size(max = 200)
    private String versionNo;

    @ApiModelProperty(value = "Client type ")
    @NotBlank
    @Size(max = 100)
    private String upgradeType;

    @ApiModelProperty(value = " Version content")
    @NotBlank
    @Size(max = 500)
    private String versionContent;

    @ApiModelProperty(value = " Wether Forced Update 1 YES 0 NO")
    @NotNull
    private Integer isForceUpdates;

}
