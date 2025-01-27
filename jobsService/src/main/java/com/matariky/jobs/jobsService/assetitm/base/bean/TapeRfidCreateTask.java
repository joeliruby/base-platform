package com.matariky.jobs.jobsService.assetitm.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:  Label  Generation  Task bean
 * @author: chenyajun
 * @create: 2024/2/08 9:48
 **/
@TableName(value = "basic_base_rfidfactory", autoResultMap = true)
@Data
public class TapeRfidCreateTask  implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = " Task Batch Number")
    private String taskBatchCode;

    @ApiModelProperty(value = " Task  Name")
    private String taskName;

    @ApiModelProperty(value = " Item ID")
    private Long goodsId;

    @ApiModelProperty(value = " Label Type ")
    private String rfidType;

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

    @ApiModelProperty(value = " Wether  Generation  Barcode ")
    private Integer isOdcode;

    @ApiModelProperty(value = " Barcode  Fixed Content")
    private String odFixedContent;

    @ApiModelProperty(value = " Wether  Generation  QR Code ")
    private Integer isQrcode;

    @ApiModelProperty(value = " QR Code  Fixed Content")
    private String qrFixedContent;

    @ApiModelProperty(value = "文件 Wether  Generation ")
    private Integer isFileCreate;

    @ApiModelProperty(value = "Download Address ")
    private String downloadUrl;

    @ApiModelProperty(value = "Download次数")
    private Integer downloadNum;

    @ApiModelProperty(value = "最新Download Time ")
    private Long downloadTime;

    @ApiModelProperty(value = " Task  Status ")
    private Integer taskStatus;



    @ApiModelProperty(value = " Remark ")
    private String remark;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
    private Long createBy;
    private Long updateBy;
    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String tenantId;

}
