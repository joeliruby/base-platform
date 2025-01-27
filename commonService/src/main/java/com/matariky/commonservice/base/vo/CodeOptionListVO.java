package com.matariky.commonservice.base.vo;

import com.matariky.model.QueryDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CodeOptionListVO extends QueryDataIsolation {

    private Long typeId;
}
