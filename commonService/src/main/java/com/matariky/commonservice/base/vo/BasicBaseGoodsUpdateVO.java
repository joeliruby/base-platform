package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasicBaseGoodsUpdateVO {

    @NotBlank
    @Size(max = 200)
    private String goodsName;

    @Size(max = 100)
    @ApiModelProperty(value = " Goods  Image ")
    private String goodsImage;

    @Size(max = 500)
    @ApiModelProperty(value = " Item  Description ")
    private String goodsDescribe;

    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;
}
