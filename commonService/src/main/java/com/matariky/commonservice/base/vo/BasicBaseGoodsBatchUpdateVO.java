package com.matariky.commonservice.base.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BasicBaseGoodsBatchUpdateVO {

    @NotNull
    private Long id;

    @NotNull
    private Long goodsId;

    @NotBlank
    private String batchCode;

    @NotNull
    private Long productionDate;

    @NotNull
    private Long validityDate;

    private String supplier;

    @NotNull
    private BigDecimal amount;

}
