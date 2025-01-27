package com.matariky.userservice.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * Automatically generated entity class
 * 
 * @author AUTOMATION
 */

@TableName("user_organization")
@Data
public class UserOrganization implements Comparable<UserOrganization> {
	@TableId("id")
	private Long id;

	private String organizationCode;

	@NotBlank(message = "机构 Name不能为空！")
	private String organizationName;

	private Long createTime;
	private Long updateTime;

	private String description;

	private String tenantId;
	private Boolean isVirtual;
	private Long createdBy;
	private Long updatedBy;

	@NotNull
	private Integer orgType;

	@NotNull
	private Integer orderNum;

	private String comment;

	private String liaisonName;

	private String liaisonMobile;

	private Long parentId;

	private Long userGroupId;

	@TableField(exist = false)
	private String tenantName;// Tenant Name 字

	@TableField(exist = false)
	private String parentName;// 上级机构 Name

	private Long deleteTime;

	@Override
	public int compareTo(UserOrganization o) {

		if (this.organizationCode.contains(o.getOrganizationCode())) {
			return 1;
		} else if (this.organizationCode.equals(o.getOrganizationCode())) {
			return 0;
		} else {
			return -1;
		}
	}

}
