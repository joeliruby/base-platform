package com.matariky.bizservice.assetitm.base.bean;

import lombok.Data;
import java.lang.String;
import java.lang.Long;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
@Data
public class BasicBaseRfidfactoryParameter {

	private Long id;
	private Long rfidfactoryId;
	private String type;
	private String parameterName;
	private String parameterContent;
	private String remark;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;

}