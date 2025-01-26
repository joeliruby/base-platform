package com.matariky.bizservice.assetitm.base.bean;

import lombok.Data;

import java.lang.String;
import java.lang.Long;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
public class BasicBaseRfidprintDetail {

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