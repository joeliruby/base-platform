package com.matariky.commonservice.base.bean;

import java.lang.String;
import com.matariky.model.QueryDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.Long;
import java.lang.Integer;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseTraceability extends QueryDataIsolation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String traceabilityCode;
	private String traceabilityName;
	private Integer status;
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