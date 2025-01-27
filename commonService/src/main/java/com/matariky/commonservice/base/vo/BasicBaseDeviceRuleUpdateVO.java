package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BasicBaseDeviceRuleUpdateVO {

    @NotNull
    private Long id;

    @NotNull
    @ApiModelProperty(value = " Wether  Record  Log ")
    private Boolean isRecordLog;

}
