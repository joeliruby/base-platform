package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddExtendFieldDetailVO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "Field name")
    @NotBlank
    private String fieldName;

    @ApiModelProperty(value = "Field Type ")
    @NotBlank
    private String fieldType;

    private String fieldMap;

    private Boolean disable;

    private Boolean isRequired = false;

}
