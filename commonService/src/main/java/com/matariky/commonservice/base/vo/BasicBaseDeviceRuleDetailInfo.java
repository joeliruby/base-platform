package com.matariky.commonservice.base.vo;


import lombok.Data;

import java.util.List;

@Data
public class BasicBaseDeviceRuleDetailInfo {

    private Long ruleId;

    private List<BasicBaseDeviceRuleDetailVO> list;

    private Boolean isRecordLog;
}
