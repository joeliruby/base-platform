package com.matariky.bizservice.assetitm.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Automatically generated entity class
 * @author AUTOMATION
 */
@Data
public class BasicBaseRfidfactoryAddVO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = " Task Batch Number")
    private String taskBatchCode;

    @ApiModelProperty(value = " Label Type ")
    private String rfidType;

    @ApiModelProperty(value = " Task  Name")
    private String taskName;

    @ApiModelProperty(value = "EPC Rule ")
    private String epcRule;

    @ApiModelProperty(value = " Requirement  Label  Quantity")
    private Integer rfidNum;

    @ApiModelProperty(value = " Yield Rate ")
    private BigDecimal yieldRate;

    @ApiModelProperty(value = " Predicted  Generation Number")
    private Integer createNum;

    @ApiModelProperty(value = " Is LockedEPC")
    private Integer isLockEpc;

    @ApiModelProperty(value = "EPC Password ")
    private String epcPassword;


    @ApiModelProperty(value = " Item ID")
    private Long goodsId;

    @ApiModelProperty(value = " Wether  Generation  Barcode ")
    private Integer isOdcode;

    @ApiModelProperty(value = " Barcode  Fixed Content")
    private String odFixedContent;

    @ApiModelProperty(value = " Wether  Generation  QR Code ")
    private Integer isQrcode;

    @ApiModelProperty(value = " QR Code  Fixed Content")
    private String qrFixedContent;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    private List<BasicBaseRfidfactoryParameterAddVO> rfidfactoryParameterAddVOS;


    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String tenantId;
    private Long createBy;
    private Long updateBy;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;

}

