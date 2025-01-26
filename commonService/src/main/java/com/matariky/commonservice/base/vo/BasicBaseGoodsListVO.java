package com.matariky.commonservice.base.vo;


import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BasicBaseGoodsListVO extends QueryDataIsolation {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "分页下标",required = true)
    private int  index;

    @ApiModelProperty(value = "每页大小",required = true)
    private int perPage;

    @ApiModelProperty(value = "货物 Name")
    private String goodsName;

    @ApiModelProperty(value = "货物编码")
    private String goodsCode;


    private List<String> extendFieldList;
}
