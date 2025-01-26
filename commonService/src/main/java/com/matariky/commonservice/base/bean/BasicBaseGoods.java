package com.matariky.commonservice.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.String;
import java.lang.Long;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
@TableName(value = "basic_base_goods")
public class BasicBaseGoods {

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "Goods Code")
	private String goodsCode;

	@ApiModelProperty(value = "Goods Name")
	private String goodsName;

	@ApiModelProperty(value = "Goods Image")
	private String goodsImage;

	@ApiModelProperty(value = "Item Description")
	private String goodsDescribe;

	@ApiModelProperty(value = "Remark")
	private String remark;

	@ApiModelProperty(value = "Creation Time")
	private Long createTime;

	@ApiModelProperty(value = "Update Time")
	private Long updateTime;

	@ApiModelProperty(value = "Deletion Time")
	private Long deleteTime;

	@ApiModelProperty(value = "Creator User ID")
	private Long createBy;

	@ApiModelProperty(value = "Updater User ID")
	private Long updateBy;

	@ApiModelProperty(value = "Operator Department Code")
	private String operatorOrgCode;

	@ApiModelProperty(value = "Operator Personal Organization Code")
	private String operatorSelfOrgCode;

	@ApiModelProperty(value = "Tenant ID")
	private String tenantId;

}
