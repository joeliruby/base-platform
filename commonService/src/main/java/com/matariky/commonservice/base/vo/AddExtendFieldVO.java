package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class AddExtendFieldVO {

    @ApiModelProperty(value = "表单名")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "字段列表")
    @Valid
    private List<AddExtendFieldInfoVO> list;


}
