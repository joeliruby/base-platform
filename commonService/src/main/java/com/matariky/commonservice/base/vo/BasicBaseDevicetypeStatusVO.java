package com.matariky.commonservice.base.vo;


import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class BasicBaseDevicetypeStatusVO {

    @NotNull
    private Long id;

    @NotNull
    private Integer status;
}
