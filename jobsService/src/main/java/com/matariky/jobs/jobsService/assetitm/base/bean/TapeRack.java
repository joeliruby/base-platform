package com.matariky.jobs.jobsService.assetitm.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
@TableName("biz_tape_rack")
public class TapeRack {

    private Long id;
    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String tenantId;
    private String locationId;
    private String rackName;
    private String rackCode;
    private Integer rackNum;
    private String rackSpecCode;
    private Double rackSpecLength;
    private Double rackSpecWidth;
    private Double rackSpecHeight;
    private String isVaild;
    private String status;
    private String remark;
    private Long createBy;
    private Long createTime;
    private Long updateBy;
    private Long updateTime;
    private Long deleteTime;
}