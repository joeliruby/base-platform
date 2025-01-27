package com.matariky.userservice.bean;

import com.matariky.utils.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TreeModel2 extends TreeNode<Object> {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pid;
	private String label;
	private String code;

	// Link Address
	private String url;

	// resource Type
	private Integer resourceType;

	// connect App
	private String applicationName;

	// icon
	private String icon;

	// Wether hide
	private boolean isActive;

	// Sort
	private Long sortOrder;

}
