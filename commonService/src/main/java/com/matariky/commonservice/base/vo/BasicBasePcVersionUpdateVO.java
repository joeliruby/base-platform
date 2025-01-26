package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBasePcVersionUpdateVO {


    @ApiModelProperty(value = "版本 Name")
    @NotBlank
    @Size(max = 200)
    private String versionName;

    @ApiModelProperty(value = "版本号")
    @NotBlank
    @Size(max = 200)
    private String versionNo;

    @ApiModelProperty(value = "版本内容")
    @NotBlank
    @Size(max = 500)
    private String versionContent;

    @ApiModelProperty(value = " Requirement 采集日期")
    @NotNull
    private Long requirementDate;

    @ApiModelProperty(value = "消息提醒自动关闭 Time ")
    @Size(max = 20)
    private String messageShutdownTime;

    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;
}
