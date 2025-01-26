package com.matariky.commonservice.network.bean;

import java.lang.String;

import com.matariky.model.QueryDataIsolation;

import java.lang.Long;
import java.lang.Integer;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class BasicBaseNetworkLog extends QueryDataIsolation{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String systemVersionNumber;
	private String deviceType;
	private String businessModule;
	private Long businessTime;
	private Integer signalLevel;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;
	public Long getId(){
		return id;
	}

	private String  accessAccount;

	public String getAccessAccount() {
		return accessAccount;
	}

	public void setAccessAccount(String accessAccount) {
		this.accessAccount = accessAccount;
	}

	public String getSystemVersionNumber(){
		return systemVersionNumber;
	}

	public String getDeviceType(){
		return deviceType;
	}

	public String getBusinessModule(){
		return businessModule;
	}

	public Long getBusinessTime(){
		return businessTime;
	}

	public Integer getSignalLevel(){
		return signalLevel;
	}

	public Long getCreateTime(){
		return createTime;
	}

	public Long getUpdateTime(){
		return updateTime;
	}

	public Long getDeleteTime(){
		return deleteTime;
	}

	public Long getCreateBy(){
		return createBy;
	}

	public Long getUpdateBy(){
		return updateBy;
	}

	public String getOperatorOrgCode(){
		return operatorOrgCode;
	}

	public String getOperatorSelfOrgCode(){
		return operatorSelfOrgCode;
	}

	public String getTenantId(){
		return tenantId;
	}

	public void setId(Long id){
		this.id = id;
	}

	public void setSystemVersionNumber(String systemVersionNumber){
		this.systemVersionNumber = systemVersionNumber;
	}

	public void setDeviceType(String deviceType){
		this.deviceType = deviceType;
	}

	public void setBusinessModule(String businessModule){
		this.businessModule = businessModule;
	}

	public void setBusinessTime(Long businessTime){
		this.businessTime = businessTime;
	}

	public void setSignalLevel(Integer signalLevel){
		this.signalLevel = signalLevel;
	}

	public void setCreateTime(Long createTime){
		this.createTime = createTime;
	}

	public void setUpdateTime(Long updateTime){
		this.updateTime = updateTime;
	}

	public void setDeleteTime(Long deleteTime){
		this.deleteTime = deleteTime;
	}

	public void setCreateBy(Long createBy){
		this.createBy = createBy;
	}

	public void setUpdateBy(Long updateBy){
		this.updateBy = updateBy;
	}

	public void setOperatorOrgCode(String operatorOrgCode){
		this.operatorOrgCode = operatorOrgCode;
	}

	public void setOperatorSelfOrgCode(String operatorSelfOrgCode){
		this.operatorSelfOrgCode = operatorSelfOrgCode;
	}

	public void setTenantId(String tenantId){
		this.tenantId = tenantId;
	}


}
