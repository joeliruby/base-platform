package com.matariky.jobs.jobsService.assetitm.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:  Label  Generation  Task bean
 * @author: chenyajun
 * @create: 2024/2/08 9:48
 **/
@TableName(value = "basic_base_rfidfactory_parameter", autoResultMap = true)
@Data
public class TapeRfidCreateParameterTask   implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long rfidfactoryId;
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
