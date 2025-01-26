package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BasicBaseDeviceTypeUpdateVO {

    @ApiModelProperty(value = " Device Type key(来自 Data 字典)")
    @NotBlank
    @Size(max = 100)
    private String typeKey;

    @ApiModelProperty(value = " Device 型号")
    @NotBlank
    @Size(max = 200)
    private String deviceModel;

    @ApiModelProperty(value = " Device 厂家")
    @NotBlank
    @Size(max = 200)
    private String deviceFactory;

    @ApiModelProperty(value = " Device 描述")
    @Size(max = 500)
    private String deviceDescribe;

    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "协议Type ")
    private String protocolType;

    @ApiModelProperty(value = " Wether 自动升级")
    private String isAutoUpgrade;

    @ApiModelProperty(value = "指令列表")
    private List<CommandVO> commandList;
}
