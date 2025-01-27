package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseDeviceListVO extends QueryDataIsolation {

    @ApiModelProperty(value = "Page Index", required = true)
    private Integer index;

    @ApiModelProperty(value = "Page Size", required = true)
    private Integer perPage;

    @ApiModelProperty(value = " Device Type ID")
    private Long typeId;

    @ApiModelProperty(value = " Device   Code ")
    private String deviceCode;

    @ApiModelProperty(value = " Device  Type ")
    private String deviceModel;

    @ApiModelProperty(value = " Device  Factory  ")
    private String deviceFactory;

    @ApiModelProperty(value = " Device  Status (on :   Online  off: Offline)")
    private String status;

    @ApiModelProperty(value = " Device Type key( From  Data   Dictionary )")
    private String typeKey;

    @ApiModelProperty(value = " Firmware  Upgrade Package ID")
    private Long packageId;

}
