package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsOptionInfo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = " Goods  Code ")
    private String goodsCode;

    @ApiModelProperty(value = " Goods  Name")
    private String goodsName;

    @ApiModelProperty(value = "Display Text ")
    private String label;
}
