package com.matariky.jobs.jobsService.assetitm.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: Label Print Task bean
 * @author: chenyajun
 * @create: 2024/2/08 9:48
 **/
@TableName(value = "basic_base_rfidprint", autoResultMap = true)
@Data
public class TapeRfidPrintTask implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = " Task Batch Number")
    private String taskName;

    @ApiModelProperty(value = " Print Type  1  Batch  Print  0 补打")
    private String printType;

    @ApiModelProperty(value = " Task  Time ")
    private Long taskTime;

    @ApiModelProperty(value = " Device ID")
    private Long deviceId;

    @ApiModelProperty(value = " Printer  Code ")
    private String printCode;

    @ApiModelProperty(value = " Printer Name")
    private String printName;

    @ApiModelProperty(value = " Item ID")
    private Long goodsId;

    @ApiModelProperty(value = " Item  Code ")
    private String goodsCode;

    @ApiModelProperty(value = " Item  Name")
    private String goodsName;

    @ApiModelProperty(value = " To Print   Quantity")
    private Integer printNum;

    @ApiModelProperty(value = " Printed  Quantity")
    private Integer printedNum;

    @ApiModelProperty(value = " Unprinted  Quantity")
    private Integer unprintNum;

    @ApiModelProperty(value = "EPC Rule ")
    private String epcRule;

    @ApiModelProperty(value = " Is LockedEPC 1是 0否")
    private Integer isLockEpc;

    @ApiModelProperty(value = "EPC Password ")
    private String epcPassword;

    @ApiModelProperty(value = " Wether  Generation  Barcode  1是 0否")
    private Integer isOdcode;

    @ApiModelProperty(value = " Barcode  Fixed Content")
    private String odFixedContent;

    @ApiModelProperty(value = " Wether  Generation  QR Code  1是 0否")
    private Integer isQrcode;

    @ApiModelProperty(value = " QR Code  Fixed Content")
    private String qrFixedContent;

    @ApiModelProperty(value = "  Label Image")
    private String rfidImg;

    @ApiModelProperty(value = " Print  Status  0  To Be Started  1  In Progress  2   Print Completed ")
    private Integer printStatus;

    @ApiModelProperty(value = " Is Locked 0 No 1 Yes")
    private Integer isLock;

    @ApiModelProperty(value = " Remark ")
    private String remark;

    @ApiModelProperty(value = " Real Name")
    private String realName;

    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
    private Long createBy;
    private Long updateBy;
    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String tenantId;
}
