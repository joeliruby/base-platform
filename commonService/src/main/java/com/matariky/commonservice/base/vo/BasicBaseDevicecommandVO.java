package com.matariky.commonservice.base.vo;

import lombok.Data;

import java.util.List;


@Data
public class BasicBaseDevicecommandVO {
    private Long commandId;
    private Boolean isAll;
    private List<PackageInfoVO> packageIds;
}
