package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicBaseDeviceListVO extends QueryDataIsolation {

    @ApiModelProperty(value = "Page Index", required = true)
    private Integer index;

    @ApiModelProperty(value = "Page Size", required = true)
    private Integer perPage;

    @ApiModelProperty(value = " Device Type ID")
    private Long typeId;

    @ApiModelProperty(value = " Device 编号")
    private String deviceCode;

    @ApiModelProperty(value = " Device 型号")
    private String deviceModel;

    @ApiModelProperty(value = " Device 厂家")
    private String deviceFactory;

    @ApiModelProperty(value = " Device  Status (on : 在线  off：离线)")
    private String status;

    @ApiModelProperty(value = " Device Type key(来自 Data 字典)")
    private String typeKey;

    @ApiModelProperty(value = "固件升级包ID")
    private Long packageId;

}
