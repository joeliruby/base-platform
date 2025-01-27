package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BasicBaseGoodsAddVO {

    @ApiModelProperty(value = " Item  Code ")
    @NotBlank
    @Size(max = 200)
    private String goodsCode;

    @ApiModelProperty(value = " Item  Name")
    @NotBlank
    @Size(max = 200)
    private String goodsName;

    @Size(max = 100)
    @ApiModelProperty(value = " Item  Image ")
    private String goodsImage;

    @Size(max = 500)
    @ApiModelProperty(value = " Item  Description ")
    private String goodsDescribe;

}
