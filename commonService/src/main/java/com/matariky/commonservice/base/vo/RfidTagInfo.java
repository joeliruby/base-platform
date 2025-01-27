package com.matariky.commonservice.base.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RfidTagInfo {

    /**
     * rfid Code
     */
    @ApiModelProperty("rfid Code ")
    private String rfidCode;

    /**
     * epc
     */
    @ApiModelProperty("epc")
    private String epc;

    /**
     * Label id
     */
    @ApiModelProperty(" Label id")
    private String tagId;

    /**
     * Label Type ：0. In/Out Label ,1. Item Label
     */
    @ApiModelProperty(" Label Type ")
    private Integer tagType;

    /**
     * Label Category ：0 Formal Label , 1临时 Label
     */
    @ApiModelProperty(" Label Category ")
    private Integer tagCategory;

    /**
     * Wether Activate
     */
    @ApiModelProperty(" Wether  Activate")
    private Boolean enable;

    /**
     * Remark
     */
    @ApiModelProperty(" Remark ")
    private String remark;

    /**
     * Occupant id（ Item id/ Visitor id）
     */
    @ApiModelProperty("  Occupant id")
    private Long occupantId;

    /**
     * Primary Key
     */
    private Long id;

    /**
     * Create Time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * Creater
     */
    private String createBy;

    /**
     * Update Time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * Updater
     */
    private String updateBy;

    /**
     * epc Pagination
     */
    private String epcListStr;

    /**
     * pec Pagination epc And tid
     */
    private List<EPCAndTIDVO> epcAndTidList;

}
