package com.matariky.jobs.jobsService.assetitm.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "basic_base_rfidprint_parameter", autoResultMap = true)
@Data
public class TapeRfidPrintParameterTask implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long printId;
    private String type;
    private String parameterName;
    private String parameterContent;
    private String remark;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
    private Long createBy;
    private Long updateBy;
    private String operatorOrgCode;
    private String operatorSelfOrgCode;
}
