package com.matariky.userservice.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.matariky.utils.TreeNode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TreeModel extends TreeNode<Object>{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pid;
	private String name;
	private String code;
	private Long inSuite;
	private boolean disabled;
	private Long  userGroupId;

	//链接地址
	private String url;

	//资源Type 
	private Integer resourceType;

	//所属应用
	private String applicationName;

	//图标
	private String icon;

	// Wether 隐藏
	private boolean isActive;

	//排序
	private Long sortOrder;


	//排序
	private boolean isCheck;
}
