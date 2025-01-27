package com.matariky.commonservice.device.bean;

import java.lang.String;
import java.math.BigDecimal;

import com.matariky.model.QueryDataIsolation;

import io.swagger.annotations.ApiModelProperty;

import java.lang.Long;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
public class BasicBaseDeviceLog extends QueryDataIsolation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String systemVersionNumber;
	private String deviceType;
	private String businessModule;
	private Long businessTime;
	private String deviceCode;
	private String apiName;
	private BigDecimal power;
	private String collectContent;
	private String physicalAddress;
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
	private String url;
	private String macAddress;
	private Long triggerTime;

	public Long getTriggerTime() {
		return triggerTime;
	}

	public void setTriggerTime(Long triggerTime) {
		this.triggerTime = triggerTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
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

	public Long getId() {
		return id;
	}

	public String getSystemVersionNumber() {
		return systemVersionNumber;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getBusinessModule() {
		return businessModule;
	}

	public Long getBusinessTime() {
		return businessTime;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public String getApiName() {
		return apiName;
	}

	public BigDecimal getPower() {
		return power;
	}

	public String getCollectContent() {
		return collectContent;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
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

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public void setBusinessModule(String businessModule) {
		this.businessModule = businessModule;
	}

	public void setBusinessTime(Long businessTime) {
		this.businessTime = businessTime;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public void setPower(BigDecimal power) {
		this.power = power;
	}

	public void setCollectContent(String collectContent) {
		this.collectContent = collectContent;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
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
