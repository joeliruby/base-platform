package com.matariky.commonservice.base.vo;

import lombok.Data;

/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
public class BasicBaseDevicePackageInfoVO {

	private Long id;
	private Long typeId;
	private String typeName;
	private String packageName;
	private String packageVersion;
	private String upgradeFile;
	private String upgradeContent;
	private String downloadQrcode;
	private Integer deviceNum;
	private String remark;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;
	private String realName;

}
