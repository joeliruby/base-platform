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

    @ApiModelProperty(value = "创建 Time ")
    private Long createTime;

    @ApiModelProperty(value = "Update Time ")
    private Long updateTime;

    @ApiModelProperty(value = "删除 Time ")
    private Long deleteTime;

    @ApiModelProperty(value = "创建用户ID")
    private Long createBy;

    @ApiModelProperty(value = "Update用户ID")
    private Long updateBy;

    @ApiModelProperty(value = "访问产生者部门编码")
    private String operatorOrgCode;

    @ApiModelProperty(value = "访问产生者个人组织机构编码")
    private String operatorSelfOrgCode;

    @ApiModelProperty(value = " Tenant ID")
    private String tenantId;

    @ApiModelProperty(value = "货物 Name")
    private String goodsName;

    @ApiModelProperty(value = "货物编码")
    private String goodsCode;

    @ApiModelProperty(value = " Device Type ID")
    private Long typeId;

    @ApiModelProperty(value = " Device 编码")
    private String deviceCode;

    @ApiModelProperty(value = " Device Type 编码")
    private String typeCode;

    @ApiModelProperty(value = " Device Type ")
    private String typeName;

    @ApiModelProperty(value = " Device 厂家")
    private String deviceFactory;

    @ApiModelProperty(value = " Device 型号")
    private String deviceModel;

    @ApiModelProperty(value = " Label 编码")
    private Long tagCode;

}
