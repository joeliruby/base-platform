package com.matariky.bizservice.assetitm.base.bean;

import java.math.BigDecimal;
import com.matariky.model.QueryDataIsolation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
@Data
public class BasicBaseRfidfactory extends QueryDataIsolation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Task Batch Number")
	private String taskBatchCode;

	@ApiModelProperty(value = "Task Name")
	private String taskName;

	@ApiModelProperty(value = "Goods ID")
	private Long goodsId;

	@ApiModelProperty(value = "Goods Code")
	private String goodsCode;

	@ApiModelProperty(value = "Goods Name")
	private String goodsName;

	@ApiModelProperty(value = "RFID Type")
	private String rfidType;

	@ApiModelProperty(value = "EPC Rule")
	private String epcRule;

	@ApiModelProperty(value = "Required Tag Quantity")
	private Integer rfidNum;

	@ApiModelProperty(value = "Yield Rate")
	private BigDecimal yieldRate;

	@ApiModelProperty(value = "Expected Quantity to Generate")
	private Integer createNum;

	@ApiModelProperty(value = "Is EPC Locked")
	private Integer isLockEpc;

	@ApiModelProperty(value = "EPC Password")
	private String epcPassword;

	@ApiModelProperty(value = "Generate One-dimensional Code")
	private Integer isOdcode;

	@ApiModelProperty(value = "One-dimensional Code Fixed Content")
	private String odFixedContent;

	@ApiModelProperty(value = "Generate QR Code")
	private Integer isQrcode;

	@ApiModelProperty(value = "QR Code Fixed Content")
	private String qrFixedContent;

	@ApiModelProperty(value = "File Created")
	private Integer isFileCreate;

	@ApiModelProperty(value = "Download URL")
	private String downloadUrl;

	@ApiModelProperty(value = "Download Count")
	private Integer downloadNum;

	@ApiModelProperty(value = "Latest Download Time")
	private Long downloadTime;

	@ApiModelProperty(value = "Upload Quantity")
	private Integer uploadNum;

	@ApiModelProperty(value = "Task Status")
	private Integer taskStatus;

	@ApiModelProperty(value = "Creation Date Start")
	private String createDateStart;

	@ApiModelProperty(value = "Creation Date End")
	private String createDateEnd;

	@ApiModelProperty(value = "Remarks")
	private String remark;

	@ApiModelProperty(value = "Real Name")
	private String realName;

	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;

}
