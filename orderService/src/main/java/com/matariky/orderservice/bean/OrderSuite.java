package com.matariky.orderservice.bean;


import java.lang.String;
import java.lang.Long;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class OrderSuite {

	private Long id;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;
	private String suiteCode;
	private String suiteName;
	private String suiteNotes;
	private String suiteStatus;
	private Long createdBy;
	private Long createTime;
	private Long updateBy;
	private Long updateTime;
	private Long deleteTime;
	public Long getId(){
		return id;
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

	public String getSuiteCode(){
		return suiteCode;
	}

	public String getSuiteName(){
		return suiteName;
	}

	public String getSuiteNotes(){
		return suiteNotes;
	}

	public String getSuiteStatus(){
		return suiteStatus;
	}

	public Long getCreatedBy(){
		return createdBy;
	}

	public Long getCreateTime(){
		return createTime;
	}

	public Long getUpdateBy(){
		return updateBy;
	}

	public Long getUpdateTime(){
		return updateTime;
	}

	public Long getDeleteTime(){
		return deleteTime;
	}

	public void setId(Long id){
		this.id = id;
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

	public void setSuiteCode(String suiteCode){
		this.suiteCode = suiteCode;
	}

	public void setSuiteName(String suiteName){
		this.suiteName = suiteName;
	}

	public void setSuiteNotes(String suiteNotes){
		this.suiteNotes = suiteNotes;
	}

	public void setSuiteStatus(String suiteStatus){
		this.suiteStatus = suiteStatus;
	}

	public void setCreatedBy(Long createdBy){
		this.createdBy = createdBy;
	}

	public void setCreateTime(Long createTime){
		this.createTime = createTime;
	}

	public void setUpdateBy(Long updateBy){
		this.updateBy = updateBy;
	}

	public void setUpdateTime(Long updateTime){
		this.updateTime = updateTime;
	}

	public void setDeleteTime(Long deleteTime){
		this.deleteTime = deleteTime;
	}


}