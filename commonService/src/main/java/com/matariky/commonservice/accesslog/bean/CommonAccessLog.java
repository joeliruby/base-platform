package com.matariky.commonservice.accesslog.bean;

import java.lang.String;
import java.lang.Long;
import java.lang.Integer;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class CommonAccessLog {
	private String id;
	private Long accessTime;
	private String tenantId;
	private String tenantName;
	private String requestMethod;
	private Integer requestStatus;
	private String client;
	private String clientIp;
	private String clientAddress;
	private String responseBody;
	private String account;
	private String realName;
	private Long userId;
	private String requestBody;
	private String contentType;
	private String requestUrl;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private Long operationName;
	private String accessTimeStr;
	private String operationNameString;

	public String getAccessTimeStr() {
		return accessTimeStr;
	}

	public void setAccessTimeStr(String accessTimeStr) {
		this.accessTimeStr = accessTimeStr;
	}

	public String getOperationNameString() {
		return operationNameString;
	}
	public void setOperationNameString(String operationNameString) {
		this.operationNameString = operationNameString;
	}
	public Long getOperationName() {
		return operationName;
	}
	public void setOperationName(Long operationName) {
		this.operationName = operationName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Long accessTime) {
		this.accessTime = accessTime;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public Integer getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(Integer requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getOperatorOrgCode() {
		return operatorOrgCode;
	}
	public void setOperatorOrgCode(String operatorOrgCode) {
		this.operatorOrgCode = operatorOrgCode;
	}
	public String getOperatorSelfOrgCode() {
		return operatorSelfOrgCode;
	}
	public void setOperatorSelfOrgCode(String operatorSelfOrgCode) {
		this.operatorSelfOrgCode = operatorSelfOrgCode;
	}


}
