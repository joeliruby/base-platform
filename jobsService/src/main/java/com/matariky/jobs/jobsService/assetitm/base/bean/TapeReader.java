package com.matariky.jobs.jobsService.assetitm.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
@TableName("biz_tape_reader")
public class TapeReader {
	private Long id;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;
	private String locationId;
	private Long rackId;
	private String code;
	private Integer status;
	private String brandModel;
	private Integer antennasNum;
	private String remark;
	private Long createdBy;
	private Long updatedBy;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
}


