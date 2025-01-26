package com.matariky.jobs.jobsService.assetitm.inout.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* Automatically generated entity class
* @author AUTOMATION
*/
@TableName("biz_tape_inout")
@Data
public class TapeInout {
	private Long id;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;
	private String serialNo;
	private Long locationId;
	private Long libraryId;
	private Integer quantity;
	private Integer beforeQuantity;
	private Integer afterQuantity;
	private Integer type;
	private Integer status;
	private Integer application;
	private String operationIp;
	private String remark;
	private Long createdBy;
	private Long updatedBy;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
}