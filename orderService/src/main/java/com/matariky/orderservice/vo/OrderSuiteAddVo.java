package com.matariky.orderservice.vo;


import lombok.Data;

import java.util.List;

/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
public class OrderSuiteAddVo {

	private Long id;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;
	private String suiteCode;
	private String suiteName;
	private String suiteNotes;
	private String suiteStatus;
	private Long createdBy;
	private Long createTime;
	private Long updateBy;
	private Long updateTime;
	private Long deleteTime;


	private List<OrderSuiteConfigVo> orderSuiteConfigVos;
	private String orderSuitePermissionVos;
}