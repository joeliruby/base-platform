package com.matariky.commonservice.error.bean;

import java.lang.String;

import com.matariky.model.QueryDataIsolation;

import io.swagger.annotations.ApiModelProperty;

import java.lang.Long;
import java.lang.Integer;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class BasicBaseErrorLog extends QueryDataIsolation{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String systemVersionNumber;
	private String deviceType;
	private String businessModule;
	private Long businessTime;
	private Integer errorLevel;
	private String apiName;
	private String url;
	private String errorContent;
	private String param;
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
	
	@ApiModelProperty(value = "业务日期开始")
	private String businessTimeStart;


	@ApiModelProperty(value = "业务日期结束")
	private String businessTimeEnd;
	
	public Long getId(){
		return id;
	}

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

	public Integer getErrorLevel(){
		return errorLevel;
	}

	public String getApiName(){
		return apiName;
	}

	public String getUrl(){
		return url;
	}

	public String getErrorContent(){
		return errorContent;
	}

	public String getParam(){
		return param;
	}

	public String getPhysicalAddress(){
		return physicalAddress;
	}

	public String getAccessAccount(){
		return accessAccount;
	}

	public String getServerIp(){
		return serverIp;
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

	public void setErrorLevel(Integer errorLevel){
		this.errorLevel = errorLevel;
	}

	public void setApiName(String apiName){
		this.apiName = apiName;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public void setErrorContent(String errorContent){
		this.errorContent = errorContent;
	}

	public void setParam(String param){
		this.param = param;
	}

	public void setPhysicalAddress(String physicalAddress){
		this.physicalAddress = physicalAddress;
	}

	public void setAccessAccount(String accessAccount){
		this.accessAccount = accessAccount;
	}

	public void setServerIp(String serverIp){
		this.serverIp = serverIp;
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