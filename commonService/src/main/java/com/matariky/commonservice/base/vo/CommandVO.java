package com.matariky.commonservice.base.vo;

import lombok.Data;

import java.util.List;

@Data
public class CommandVO {

    private Long commandId;

    private List<Long> packageIds;
}
