package com.matariky.commonservice.base.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description: rfid Label  Information 表实体对象
 * @author: bo.chen
 * @create: 2022/7/11 14:05
 **/
@Data
public class RfidTagInfo {

    /**
     * rfid编码
     */
    @ApiModelProperty("rfid编码")
    private String rfidCode;

    /**
     * epc
     */
    @ApiModelProperty("epc")
    private String epc;

    /**
     *  Label id
     */
    @ApiModelProperty(" Label id")
    private String tagId;

    /**
     *  Label Type ：0出入 Label ，1. Item  Label 
     */
    @ApiModelProperty(" Label Type ")
    private Integer tagType;

    /**
     *  Label 类别：0正式 Label , 1临时 Label 
     */
    @ApiModelProperty(" Label 类别")
    private Integer tagCategory;

    /**
     *  Wether 启用
     */
    @ApiModelProperty(" Wether 启用")
    private Boolean enable;

    /**
     *  Remark 
     */
    @ApiModelProperty(" Remark ")
    private String remark;

    /**
     * 使用者id（ Item id/访客id）
     */
    @ApiModelProperty("使用者id")
    private Long occupantId;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建 Time 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改 Time 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * epc 列表字符串
     */
    private String epcListStr;

    /**
     * pec 列表 epc与tid
     */
    private List<EPCAndTIDVO> epcAndTidList;

}
