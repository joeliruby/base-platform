package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseDeviceUpgradeListVO extends QueryDataIsolation {

    @ApiModelProperty(value = "Page Index", required = true)
    private Integer index;

    @ApiModelProperty(value = "Page Size", required = true)
    private Integer perPage;

    @ApiModelProperty(value = "  Upgrade Package Name")
    private String packageName;

    @ApiModelProperty(value = "  Upgrade Package Version Number")
    private String packageVersion;

    @ApiModelProperty(value = " Upgrade  Description ")
    private String upgradeContent;

    @ApiModelProperty(value = " Device Type Id")
    private Long typeId;
}
