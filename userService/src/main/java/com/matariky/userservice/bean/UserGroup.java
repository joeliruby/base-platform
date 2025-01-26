package com.matariky.userservice.bean;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
@TableName("user_group")
@Data
public class UserGroup {
	@TableId
	private Long id;
	
	@NotBlank(message="分组 Name不能为空！")
	private String groupName;
	
	private String tenantId;
	
	private String description;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private String comment;
	private Long createdBy;
	private Long updatedBy;
	private Integer sort;
	
	//所属角色集合
  	@TableField(exist = false)
  	private List<Long> roleIdList;
  	
  	//所属用户集合
  	@TableField(exist = false)
  	private List<Long> userIdList;
  	
  	@TableField(exist = false)
  	List<String> userNameList;
	
  	@TableField(exist = false)
	List<String> roleNameList;
  	
  	

	@TableField(exist = false)
  	private String roleName;
  	
  	@TableField(exist = false)
  	private String applicationName;
  	
  	@TableField(exist = false)
  	private String loginName;
  	
  	
  	@TableField(exist = false)
  	private String tenantName;
  	
  	private String orgCode;

  	
  	
	
	


}