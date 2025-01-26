package com.matariky.commonservice.base.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class BasicBaseAppVersionDelVO {

    @NotEmpty
    private List<Long> ids;

}
