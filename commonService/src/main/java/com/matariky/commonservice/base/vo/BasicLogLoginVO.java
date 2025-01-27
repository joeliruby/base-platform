package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicLogLoginVO extends QueryDataIsolation {

    @ApiModelProperty(value = " User  Name")
    private String userName;

    @ApiModelProperty(value = " Login  Address ")
    private String loginAddress;

    @ApiModelProperty(value = " Tenant ")
    private String tenantName;

    @ApiModelProperty(value = " Session Start   Time ")
    private String createDateStart;

    @ApiModelProperty(value = " Session End Time ")
    private String createDateEnd;

    @ApiModelProperty(value = "  Current Page Index ")
    private Integer index;

    @ApiModelProperty(value = "Page Size")
    private Integer perPage;

    private Long createStartTime;

    private Long createEndTime;
}
