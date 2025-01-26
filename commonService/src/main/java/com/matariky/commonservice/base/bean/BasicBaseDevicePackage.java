package com.matariky.commonservice.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
@TableName("basic_base_devicepackage")
public class BasicBaseDevicePackage {

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
	private String  md5;
}
