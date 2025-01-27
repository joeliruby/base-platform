package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddExtendFieldInfoVO {

    @ApiModelProperty(value = "Field name")
    @NotBlank
    private String fieldName;

    @ApiModelProperty(value = "Field Type ")
    @NotBlank
    private String fieldType;

    @ApiModelProperty(value = "Field Type ")
    private Boolean isRequired;

    private Long id;

}
