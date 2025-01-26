package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBaseCodingRulesAddVO {

    @ApiModelProperty(value = " Rule  Name")
    @NotBlank
    @Size(max = 200)
    private String rulesName;

    @ApiModelProperty(value = "编码长度")
    @NotNull
    @Min(1)
    private Integer codingLength;

    @ApiModelProperty(value = " Remark ")
    @Size(max = 500)
    private String remark;

    @ApiModelProperty(value = " Rule Type ")
    @NotNull
    private Integer rulesType;

    @ApiModelProperty(value = " Rule 编码")
    @NotBlank
    private String  rulesCode;
}
