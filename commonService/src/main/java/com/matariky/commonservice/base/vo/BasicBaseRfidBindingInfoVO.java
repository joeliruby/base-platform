package com.matariky.commonservice.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class BasicBaseRfidBindingInfoVO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = " Label ID")
    private Long rfidId;

    @ApiModelProperty(value = " Item ID")
    private Long goodsId;

    @ApiModelProperty(value = " Device ID")
    private Long deviceId;

    @ApiModelProperty(value = "EPC")
    private String epc;

    @ApiModelProperty(value = "TID")
    private String tid;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    @ApiModelProperty(value = "Create  Time ")
    private Long createTime;

    @ApiModelProperty(value = "Update Time ")
    private Long updateTime;

    @ApiModelProperty(value = "Delete  Time ")
    private Long deleteTime;

    @ApiModelProperty(value = "Create  User ID")
    private Long createBy;

    @ApiModelProperty(value = "Update User ID")
    private Long updateBy;

    @ApiModelProperty(value = "Vistiting Operator Department Code ")
    private String operatorOrgCode;

    @ApiModelProperty(value = "Vistitor Organization Code Code ")
    private String operatorSelfOrgCode;

    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;

    @ApiModelProperty(value = " Goods  Name")
    private String goodsName;

    @ApiModelProperty(value = " Goods  Code ")
    private String goodsCode;

    @ApiModelProperty(value = " Device Type ID")
    private Long typeId;

    @ApiModelProperty(value = " Device  Code ")
    private String deviceCode;

    @ApiModelProperty(value = " Device Type  Code ")
    private String typeCode;

    @ApiModelProperty(value = " Device Type ")
    private String typeName;

    @ApiModelProperty(value = " Device  Factory  ")
    private String deviceFactory;

    @ApiModelProperty(value = " Device  Type ")
    private String deviceModel;

    @ApiModelProperty(value = " Label  Code ")
    private Long tagCode;

}
