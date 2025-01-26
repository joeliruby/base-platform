package com.matariky.userservice.bean;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
@TableName("user_application")
@Data
public class UserApplication {

	
	

	@TableId
	private Long id;
	
	private Boolean isActive;
	
	@TableField(exist = false)
	private String name;
	
	private Integer applicationType;
	
	@TableField(exist = false)
	private String applicationTypeName;
	
	@TableField(exist = false)
	private String businessTypeName;
	
	private String applicationName;
	
	private String versionNumber;
	
	private String icon;
	
	private String description;
	
	private Long createTime;
	
	private Long updateTime;
	
	private String tenantId;
	
	private Long createdBy;
	
	private Long updatedBy;
	
	private String entryUrl;
	
	private Long expiryTime;
	
	private Long deleteTime;
	
	private Integer businessType;
	
	private Long activityTimeout;
	


	@TableField(exist = false)
	private List<Long> permissionIdList;
	
  	@TableField(exist = false)
	private List<TreeModel> permissionTreeModels;
	
	
  	@TableField(exist = false)
	private List<TreeModel> permissionRTree;
	
	}