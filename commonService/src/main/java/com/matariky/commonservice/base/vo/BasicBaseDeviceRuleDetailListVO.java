package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseDeviceRuleDetailListVO extends QueryDataIsolation {

    @ApiModelProperty(value = " Device Type ID")
    @NotNull
    private Long typeId;

    private Long ruleId;
}
