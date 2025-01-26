package com.matariky.commonservice.base.vo;

import lombok.Data;

@Data
public class DeviceCodeInfo {

    private Long id;

    private String typeName;

    private String DeviceCode;

    private String deviceFactory;

    private String deviceModel;

    private String label;
}
