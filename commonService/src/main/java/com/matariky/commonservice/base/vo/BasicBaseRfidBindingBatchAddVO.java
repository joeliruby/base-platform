package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BasicBaseRfidBindingBatchAddVO {

    @ApiModelProperty(value = " Item ID")
    @NotNull
    private Long goodsId;

    @ApiModelProperty(value = " Device ID")
    @NotNull
    private Long deviceId;

    @ApiModelProperty(value = "epc And tid Pagination ")
    @NotEmpty
    @Valid
    private List<EPCAndTIDVO> epcAndTidList;

}
