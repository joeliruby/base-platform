package com.matariky.commonservice.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
@Data
@TableName("basic_base_goods_batch")
public class BasicBaseGoodsBatch  {

	private Long id;
	private Long goodsId;
	private String batchCode;
	private Long productionDate;
	private Long validityDate;
	private String supplier;
	private BigDecimal amount;
	private String remark;
	private Long createTime;
	private Long updateTime;
	private Long deleteTime;
	private Long createBy;
	private Long updateBy;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String tenantId;

}
