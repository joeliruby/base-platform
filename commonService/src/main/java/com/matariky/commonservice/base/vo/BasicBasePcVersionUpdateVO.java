package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBasePcVersionUpdateVO {

    @ApiModelProperty(value = " Version Name")
    @NotBlank
    @Size(max = 200)
    private String versionName;

    @ApiModelProperty(value = " Version Number")
    @NotBlank
    @Size(max = 200)
    private String versionNo;

    @ApiModelProperty(value = "  Version Content ")
    @NotBlank
    @Size(max = 500)
    private String versionContent;

    @ApiModelProperty(value = " Requirement  Collection Date ")
    @NotNull
    private Long requirementDate;

    @ApiModelProperty(value = "  Message  Alert  Automatic Close  Time ")
    @Size(max = 20)
    private String messageShutdownTime;

    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;
}
