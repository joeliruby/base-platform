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

    @ApiModelProperty(value = "升级包 Name")
    private String packageName;

    @ApiModelProperty(value = "升级包版本号")
    private String packageVersion;

    @ApiModelProperty(value = "升级描述")
    private String upgradeContent;

    @ApiModelProperty(value = " Device Type Id")
    private Long  typeId;
}
