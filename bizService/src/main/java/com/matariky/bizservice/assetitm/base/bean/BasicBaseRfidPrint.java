package com.matariky.bizservice.assetitm.base.bean;

import io.swagger.annotations.ApiModelProperty;

import java.lang.String;
import com.matariky.model.QueryDataIsolation;
import lombok.Data;
import java.lang.Long;
import java.lang.Integer;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
@Data
public class BasicBaseRfidPrint extends QueryDataIsolation {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Task Batch Number")
	private String taskName;

	@ApiModelProperty(value = "Print Type: 1 Batch Print, 0 Reprint")
	private String printType;

	@ApiModelProperty(value = "Task Time")
	private Long taskTime;

	@ApiModelProperty(value = "Device ID")
	private Long deviceId;

	@ApiModelProperty(value = "Printer Code")
	private String printCode;

	@ApiModelProperty(value = "Printer Name")
	private String printName;

	@ApiModelProperty(value = "Goods ID")
	private Long goodsId;

	@ApiModelProperty(value = "Goods Code")
	private String goodsCode;

	@ApiModelProperty(value = "Goods Name")
	private String goodsName;

	@ApiModelProperty(value = "Required Print Quantity")
	private Integer printNum;

	@ApiModelProperty(value = "Printed Quantity")
	private Integer printedNum;

	@ApiModelProperty(value = "Unprinted Quantity")
	private Integer unprintNum;

	@ApiModelProperty(value = "EPC Rule")
	private String epcRule;

	@ApiModelProperty(value = "Is EPC Locked: 1 Yes, 0 No")
	private Integer isLockEpc;

	@ApiModelProperty(value = "EPC Password")
	private String epcPassword;

	@ApiModelProperty(value = "Generate One-dimensional Code: 1 Yes, 0 No")
	private Integer isOdcode;

	@ApiModelProperty(value = "One-dimensional Code Fixed Content")
	private String odFixedContent;

	@ApiModelProperty(value = "Generate QR Code: 1 Yes, 0 No")
	private Integer isQrcode;

	@ApiModelProperty(value = "QR Code Fixed Content")
	private String qrFixedContent;

	@ApiModelProperty(value = "Tag Image")
	private String rfidImg;

	@ApiModelProperty(value = "Print Status: 0 Not Started, 1 In Progress, 2 Completed")
	private Integer printStatus;

	@ApiModelProperty(value = "Is Locked: 0 No, 1 Yes")
	private Integer isLock;

	@ApiModelProperty(value = "Remarks")
	private String remark;

	@ApiModelProperty(value = "Real Name")
	private String realName;

	@ApiModelProperty(value = "Project Name")
	private String projectName;

	@ApiModelProperty(value = "Is Printed: 0 No, 1 Yes")
	private Integer isPrint;

	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;

	@ApiModelProperty(value = "Operation Start Date")
	private String operateDateStart;

	@ApiModelProperty(value = "Operation End Date")
	private String operateDateEnd;

}
