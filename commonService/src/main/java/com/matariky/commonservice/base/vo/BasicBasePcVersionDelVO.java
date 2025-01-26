package com.matariky.commonservice.base.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class BasicBasePcVersionDelVO {

    @NotEmpty
    private List<Long> ids;

}
