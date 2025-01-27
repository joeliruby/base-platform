package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseGoodsListVO extends QueryDataIsolation {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Pagination Index", required = true)
    private int index;

    @ApiModelProperty(value = "Page Size", required = true)
    private int perPage;

    @ApiModelProperty(value = " Goods  Name")
    private String goodsName;

    @ApiModelProperty(value = " Goods  Code ")
    private String goodsCode;

    private List<String> extendFieldList;
}
