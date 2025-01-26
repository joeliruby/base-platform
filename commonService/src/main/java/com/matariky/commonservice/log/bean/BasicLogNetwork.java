package com.matariky.commonservice.log.bean;

import java.lang.String;
import java.lang.Long;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class BasicLogNetwork {

	private Long id;
	private String accessTerminals;
	private String accessModule;
	private String networkSignal;
	private Long businessTime;
	private String accessAccount;
	private Long logTime;
	private String remark;
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

	public String getAccessTerminals(){
		return accessTerminals;
	}

	public String getAccessModule(){
		return accessModule;
	}

	public String getNetworkSignal(){
		return networkSignal;
	}

	public Long getBusinessTime(){
		return businessTime;
	}

	public String getAccessAccount(){
		return accessAccount;
	}

	public Long getLogTime(){
		return logTime;
	}

	public String getRemark(){
		return remark;
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

	public void setId(Long id){
		this.id = id;
	}

	public void setAccessTerminals(String accessTerminals){
		this.accessTerminals = accessTerminals;
	}

	public void setAccessModule(String accessModule){
		this.accessModule = accessModule;
	}

	public void setNetworkSignal(String networkSignal){
		this.networkSignal = networkSignal;
	}

	public void setBusinessTime(Long businessTime){
		this.businessTime = businessTime;
	}

	public void setAccessAccount(String accessAccount){
		this.accessAccount = accessAccount;
	}

	public void setLogTime(Long logTime){
		this.logTime = logTime;
	}

	public void setRemark(String remark){
		this.remark = remark;
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

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}