package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseRfidBindingListVO extends QueryDataIsolation {

    @ApiModelProperty(value = "Pagination Index")
    private Integer index;

    @ApiModelProperty(value = "Page Size")
    private Integer perPage;

    @ApiModelProperty(value = " Item  Name")
    private String goodsName;

    @ApiModelProperty(value = " Item  Code ")
    private String goodsCode;

    @ApiModelProperty(value = " Item ID")
    private Long goodsId;

}
