package com.matariky.userservice.bean;

import com.matariky.utils.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TreeModel2 extends TreeNode<Object>{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pid;
	private String label;
	private String code;
	
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
	
}
