package com.matariky.bizservice.assetitm.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Automatically generated entity class
 * @author AUTOMATION
 */
@Data
public class BasicBaseRfidprintParameterAddVO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = " Label  Print ID")
    private Long printId;

    private String type;

    @ApiModelProperty(value = " Parameter  Name")
    private String parameterName;

    @ApiModelProperty(value = " Parameter  Source ")
    private String parameterContent;
    private String tenantId;
    private String remark;
    private Long createdBy;
    private Long updatedBy;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
}
