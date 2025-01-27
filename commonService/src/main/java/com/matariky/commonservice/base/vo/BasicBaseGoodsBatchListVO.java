package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BasicBaseGoodsBatchListVO extends QueryDataIsolation {

    @ApiModelProperty(value = "Pagination Index", required = true)
    private int index;

    @ApiModelProperty(value = "Page Size", required = true)
    private int perPage;

    @ApiModelProperty(value = " Item ID")
    @NotNull
    private Long goodsId;

    @ApiModelProperty(value = "Batch Number")
    private String batchCode;
}
