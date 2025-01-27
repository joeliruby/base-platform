package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpgradeListVO extends QueryDataIsolation {

    private Integer upgradeStatus;

    private Long packageId;
}
