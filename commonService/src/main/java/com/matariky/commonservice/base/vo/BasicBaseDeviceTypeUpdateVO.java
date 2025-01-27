package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BasicBaseDeviceTypeUpdateVO {

    @ApiModelProperty(value = " Device Type key( From  Data   Dictionary )")
    @NotBlank
    @Size(max = 100)
    private String typeKey;

    @ApiModelProperty(value = " Device  Type ")
    @NotBlank
    @Size(max = 200)
    private String deviceModel;

    @ApiModelProperty(value = " Device  Factory  ")
    @NotBlank
    @Size(max = 200)
    private String deviceFactory;

    @ApiModelProperty(value = " Device  Description ")
    @Size(max = 500)
    private String deviceDescribe;

    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "  Protocol Type ")
    private String protocolType;

    @ApiModelProperty(value = " Wether  Automatic Upgrade ")
    private String isAutoUpgrade;

    @ApiModelProperty(value = " Command  Pagination ")
    private List<CommandVO> commandList;
}
