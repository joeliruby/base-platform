package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsOptionInfo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "货物编码")
    private String goodsCode;

    @ApiModelProperty(value = "货物 Name")
    private String goodsName;

    @ApiModelProperty(value = "显示文本")
    private String label;
}
