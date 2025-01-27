package com.matariky.userservice.bean;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */
@TableName("user_role")
@Data
public class UserRole {
	@TableId("id")
	private Long id;

	private String tenantId;

	@NotBlank(message = "Character name cannot be empty!")
	private String roleName;

	private Long createTime;

	private String description;

	private Long updateTime;

	private Long deleteTime;

	private String comment;

	private Long createdBy;

	private Long updatedBy;

	@TableField(exist = false)
	private String applicationName;

	@TableField(exist = false)
	private String tenantName;

}