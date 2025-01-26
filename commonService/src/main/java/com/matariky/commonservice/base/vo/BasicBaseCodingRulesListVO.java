package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseCodingRulesListVO extends QueryDataIsolation {

    @ApiModelProperty(value = "Page Index", required = true)
    private Integer index;

    @ApiModelProperty(value = "Page Size", required = true)
    private Integer perPage;

    @ApiModelProperty(value = "Page Size")
    private String rulesName;

    @ApiModelProperty(value = " Rule Type ，来自 Data 字典")
    private Integer rulesType;

    @ApiModelProperty(value = " Status （0：停用，1：启用）")
    private Integer status;
}
