package com.matariky.jobs.jobsService.assetitm.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:  Label  Print  Task  Detailbean
 * @author: chenyajun
 * @create: 2024/2/08 9:48
 **/
@TableName(value = "basic_base_rfidprint_detail", autoResultMap = true)
@Data
public class TapeRfidPrintDetailTask   implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long printId;
    private Long rfidId;
    private Integer isPrint;
    private String epc;
    private String tid;
    private Long printTime;
    private String printTimeStart;
    private String printTimeEnd;
    private String remark;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
    private Long createBy;
    private Long updateBy;
    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String odContent;
    private String qrContent;
    private String password;
    private String rfidImg;
}
