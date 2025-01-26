package com.matariky.orderservice.bean;

import java.lang.String;
import java.lang.Long;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class OrderSuitePermission {

	private Long id;
	private String suiteCode;
	private Long permissionId;
	public Long getId(){
		return id;
	}

	public String getSuiteCode(){
		return suiteCode;
	}

	public Long getPermissionId(){
		return permissionId;
	}

	public void setId(Long id){
		this.id = id;
	}

	public void setSuiteCode(String suiteCode){
		this.suiteCode = suiteCode;
	}

	public void setPermissionId(Long permissionId){
		this.permissionId = permissionId;
	}


}