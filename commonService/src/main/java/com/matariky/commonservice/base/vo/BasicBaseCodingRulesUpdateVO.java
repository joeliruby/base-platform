package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBaseCodingRulesUpdateVO {

    @ApiModelProperty(value = " Rule  Name")
    @NotBlank
    @Size(max = 200)
    private String rulesName;

    @ApiModelProperty(value = " Code  length ")
    @NotNull
    private Integer codingLength;

    @ApiModelProperty(value = " Remark ")
    @Size(max = 500)
    private String remark;

    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;

    @ApiModelProperty(value = " Rule Type ")
    @NotNull
    private Integer rulesType;

    @ApiModelProperty(value = " Wether In order Generation ")
    @NotNull
    private Boolean isGenerateBySort;

}
