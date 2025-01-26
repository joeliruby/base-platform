package com.matariky.jobs.jobsService.assetitm.stock.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
@TableName("biz_tape_stock")
public class TapeStock {

	private Long id;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;
	private String locationId;
	private Long libraryId;
	private Integer quantity;
	private Long createdBy;
	private Long updatedBy;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
}
