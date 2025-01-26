package com.matariky.jobs.jobsService.assetitm.base.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* Automatically generated entity class
* @author AUTOMATION
*/
@TableName("biz_tape_label")
@Data
public class TapeLabel {
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;
	private String tenantId;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String barcode;
	private String epc;
	private String tid;
	private Long createdBy;
	private Long updatedBy;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;

}


