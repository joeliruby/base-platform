package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BatchGoodsBindVO {

    @ApiModelProperty(value = " Item ID")
    @NotNull
    private Long goodsId;


    List<BasicBaseRfidBindingBatchVO> list;

}
