package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseCodingRulesListVO extends QueryDataIsolation {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Page Index", required = true)
    private Integer index;

    @ApiModelProperty(value = "Page Size", required = true)
    private Integer perPage;

    @ApiModelProperty(value = "Page Size")
    private String rulesName;

    @ApiModelProperty(value = " Rule Type , From  Data   Dictionary ")
    private Integer rulesType;

    @ApiModelProperty(value = " Status 0: Deactivate,1: Activate")
    private Integer status;
}
