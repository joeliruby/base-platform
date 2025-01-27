package com.matariky.commonservice.base.bean;

import java.lang.String;
import com.matariky.model.QueryDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.Long;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseDevicecommandPackage extends QueryDataIsolation {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long typeId;
	private Long commandId;
	private Long packageId;
	private String remark;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;
	private String md5;

}