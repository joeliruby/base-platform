package com.matariky.commonservice.base.bean;

import lombok.Data;

import java.lang.String;
import java.lang.Long;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
public class BasicBaseRfidInfo {

	private Long id;
	private String tagCode;
	private String epc;
	private String tid;
	private String odContent;
	private String qrContent;
	private String password;
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
