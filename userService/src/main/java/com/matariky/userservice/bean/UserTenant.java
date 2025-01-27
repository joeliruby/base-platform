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

	@NotBlank(message = " Tenant  Name不能为空！")
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

	// 上级 Tenant Code
	@TableField(exist = false)

	private String parentCode;

	// Description
	private String description;

	private Boolean isActive;
	private String domainName;
	private String theme;// 主题颜色

	@TableField(exist = false)
	private String parentName;

	@TableField(exist = false)
	private List<Map<String, Object>> applicationIds;

	private Long parentId;

}
