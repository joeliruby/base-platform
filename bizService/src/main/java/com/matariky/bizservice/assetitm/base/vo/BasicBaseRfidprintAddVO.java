package com.matariky.bizservice.assetitm.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Automatically generated entity class
 * @author AUTOMATION
 */
@Data
public class BasicBaseRfidprintAddVO {

    @ApiModelProperty(value = "ID")
    private Long id;


    @ApiModelProperty(value = " Task  Name")
    private String taskName;

    @ApiModelProperty(value = " Task  Time ")
    private Long taskTime;

    @ApiModelProperty(value = " Device ID")
    private Long deviceId;

    @ApiModelProperty(value = " Item ID")
    private Long goodsId;

    @ApiModelProperty(value = " To Print   Quantity")
    private Integer printNum;

    @ApiModelProperty(value = " Printed  Quantity")
    private Integer printedNum;

    @ApiModelProperty(value = " Unprinted  Quantity")
    private Integer unprintNum;

    @ApiModelProperty(value = "EPC Rule ")
    private String epcRule;

    @ApiModelProperty(value = " Is LockedEPC")
    private Integer isLockEpc;

    @ApiModelProperty(value = "EPC Password ")
    private String epcPassword;

    @ApiModelProperty(value = " Wether  Generation  Barcode ")
    private Integer isOdcode;

    @ApiModelProperty(value = " Barcode  Fixed Content")
    private String odFixedContent;

    @ApiModelProperty(value = " Wether  Generation  QR Code ")
    private Integer isQrcode;

    @ApiModelProperty(value = " QR Code  Fixed Content")
    private String qrFixedContent;

    @ApiModelProperty(value = "  Label Image")
    private String rfidImg;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    @ApiModelProperty(value = " Print  Status  0  To Be Started  1  In Progress  2   Print Completed ")
    private Integer printStatus;

    @ApiModelProperty(value = " Is Locked 0 No 1 Yes")
    private Integer isLock;

    private List<BasicBaseRfidprintParameterAddVO> baseRfidprintParameterAddVOS;

    private List<BasicBaseRfidprintParameterAddVO> rfidfactoryParameterAddVOS;

    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String tenantId;
    private Long createBy;
    private Long updateBy;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
}
