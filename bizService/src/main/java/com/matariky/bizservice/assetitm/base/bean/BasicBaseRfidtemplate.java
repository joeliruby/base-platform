package com.matariky.bizservice.assetitm.base.bean;

import java.lang.String;

import com.matariky.model.QueryDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.lang.Long;
import java.lang.Integer;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseRfidtemplate extends QueryDataIsolation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String projectName;
	private String templateName;
	private Long rulesId;
	private String epcRule;
	private Integer isLockEpc;
	private String epcPassword;
	private Long goodsId;
	private Integer isOdcode;
	private String odFixedContent;
	private Integer isQrcode;
	private String qrFixedContent;
	private BigDecimal rfidLength;
	private BigDecimal rfidWidth;
	private String rfidAiimg;
	private String rfidIndicateImg;
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