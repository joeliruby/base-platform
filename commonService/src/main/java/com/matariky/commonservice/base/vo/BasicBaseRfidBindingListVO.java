package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseRfidBindingListVO extends QueryDataIsolation {

    @ApiModelProperty(value = "分页下标")
    private Integer index;

    @ApiModelProperty(value = "每页大小")
    private Integer perPage;

    @ApiModelProperty(value = " Item  Name")
    private String goodsName;

    @ApiModelProperty(value = " Item 编码")
    private String goodsCode;

    @ApiModelProperty(value = " Item ID")
    private Long  goodsId;

}
