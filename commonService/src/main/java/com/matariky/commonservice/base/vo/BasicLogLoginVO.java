package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicLogLoginVO extends QueryDataIsolation {

    @ApiModelProperty(value = "用户 Name")
    private String userName;

    @ApiModelProperty(value = "登陆地址")
    private String loginAddress;

    @ApiModelProperty(value = " Tenant ")
    private String tenantName;

    @ApiModelProperty(value = "登录开始 Time ")
    private String createDateStart;

    @ApiModelProperty(value = "登录结束 Time ")
    private String createDateEnd;

    @ApiModelProperty(value = "当前页数")
    private Integer index;

    @ApiModelProperty(value = "每页大小")
    private Integer perPage;

    private Long  createStartTime;

    private Long  createEndTime;
}
