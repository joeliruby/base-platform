package com.matariky.commonservice.base.bean;

import lombok.Data;

import java.lang.String;
import java.lang.Long;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
public class BasicBaseCreaterfidPrint {

	private Long id;
	private Long printId;
	private Long goodsId;
	private Long deviceId;
	private Long rfidId;
	private String remark;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;

}