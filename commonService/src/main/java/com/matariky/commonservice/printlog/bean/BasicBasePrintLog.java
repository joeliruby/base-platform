package com.matariky.commonservice.printlog.bean;

import java.lang.String;

import com.matariky.model.QueryDataIsolation;

import io.swagger.annotations.ApiModelProperty;

import java.lang.Long;
import java.lang.Integer;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
public class BasicBasePrintLog extends QueryDataIsolation {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String systemVersionNumber;
	private String printTaskNumber;
	private String printDetailId;
	private Long printTime;
	private Integer printStatus;
	private String sku;
	private String printContent;
	private String rfidSetting;
	private String deviceCode;
	private String macAddress;
	private String accessAccount;
	private String serverIp;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;

	private Long businessTime;
	private String returnTid;
	private String goodsCode;

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	@ApiModelProperty(value = " Business  Start Date")
	private String businessTimeStart;

	@ApiModelProperty(value = " Business End Date")
	private String businessTimeEnd;

	public String getBusinessTimeStart() {
		return businessTimeStart;
	}

	public void setBusinessTimeStart(String businessTimeStart) {
		this.businessTimeStart = businessTimeStart;
	}

	public String getBusinessTimeEnd() {
		return businessTimeEnd;
	}

	public void setBusinessTimeEnd(String businessTimeEnd) {
		this.businessTimeEnd = businessTimeEnd;
	}

	@ApiModelProperty(value = " Business  Start Date")
	private String printTimeStart;

	@ApiModelProperty(value = " Business End Date")
	private String printTimeEnd;

	public Long getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(Long businessTime) {
		this.businessTime = businessTime;
	}

	public String getReturnTid() {
		return returnTid;
	}

	public void setReturnTid(String returnTid) {
		this.returnTid = returnTid;
	}

	public Long getId() {
		return id;
	}

	public String getPrintTimeStart() {
		return printTimeStart;
	}

	public void setPrintTimeStart(String printTimeStart) {
		this.printTimeStart = printTimeStart;
	}

	public String getPrintTimeEnd() {
		return printTimeEnd;
	}

	public void setPrintTimeEnd(String printTimeEnd) {
		this.printTimeEnd = printTimeEnd;
	}

	public String getSystemVersionNumber() {
		return systemVersionNumber;
	}

	public String getPrintTaskNumber() {
		return printTaskNumber;
	}

	public String getPrintDetailId() {
		return printDetailId;
	}

	public Long getPrintTime() {
		return printTime;
	}

	public Integer getPrintStatus() {
		return printStatus;
	}

	public String getSku() {
		return sku;
	}

	public String getPrintContent() {
		return printContent;
	}

	public String getRfidSetting() {
		return rfidSetting;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public String getAccessAccount() {
		return accessAccount;
	}

	public String getServerIp() {
		return serverIp;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public Long getDeleteTime() {
		return deleteTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public String getOperatorOrgCode() {
		return operatorOrgCode;
	}

	public String getOperatorSelfOrgCode() {
		return operatorSelfOrgCode;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSystemVersionNumber(String systemVersionNumber) {
		this.systemVersionNumber = systemVersionNumber;
	}

	public void setPrintTaskNumber(String printTaskNumber) {
		this.printTaskNumber = printTaskNumber;
	}

	public void setPrintDetailId(String printDetailId) {
		this.printDetailId = printDetailId;
	}

	public void setPrintTime(Long printTime) {
		this.printTime = printTime;
	}

	public void setPrintStatus(Integer printStatus) {
		this.printStatus = printStatus;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setPrintContent(String printContent) {
		this.printContent = printContent;
	}

	public void setRfidSetting(String rfidSetting) {
		this.rfidSetting = rfidSetting;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public void setAccessAccount(String accessAccount) {
		this.accessAccount = accessAccount;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public void setDeleteTime(Long deleteTime) {
		this.deleteTime = deleteTime;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public void setOperatorOrgCode(String operatorOrgCode) {
		this.operatorOrgCode = operatorOrgCode;
	}

	public void setOperatorSelfOrgCode(String operatorSelfOrgCode) {
		this.operatorSelfOrgCode = operatorSelfOrgCode;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}
