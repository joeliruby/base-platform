package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddExtendFieldInfoVO {

    @ApiModelProperty(value = "字段名")
    @NotBlank
    private String fieldName;

    @ApiModelProperty(value = "字段Type ")
    @NotBlank
    private String fieldType;

    @ApiModelProperty(value = "字段Type ")
    private Boolean  isRequired;

    private  Long  id;

}
