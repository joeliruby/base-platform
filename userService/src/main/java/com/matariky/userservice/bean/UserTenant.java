package com.matariky.userservice.bean;

import java.util.List;
import java.util.Map;

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
@TableName("user_tenant")
@Data
public class UserTenant {
	@TableId("id")
	private String tenantCode;

	@NotBlank(message = " Tenant name cannot be empty!")
	private String tenantName;

	private Long updateTime;

	private Long deleteTime;

	private Long createTime;

	private Long id;
	private Long createdBy;
	private Long updatedBy;
	private String address;

	private String liaison;

	private String liaisonMobile;

	private Integer orderNum;

	// Superior Tenant Code
	@TableField(exist = false)

	private String parentCode;

	// Description
	private String description;

	private Boolean isActive;
	private String domainName;
	private String theme;// Theme color

	@TableField(exist = false)
	private String parentName;

	@TableField(exist = false)
	private List<Map<String, Object>> applicationIds;

	private Long parentId;

}
