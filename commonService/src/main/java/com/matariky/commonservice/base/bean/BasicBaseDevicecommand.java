package com.matariky.commonservice.base.bean;

import com.matariky.model.QueryDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicBaseDevicecommand extends QueryDataIsolation {

	private static final long serialVersionUID = 1L;
	private Long id;
    private String commandName;
    private String protocolType;
    private String commandContent;
    private String remark;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
    private Long createBy;
    private Long updateBy;
    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String tenantId;
    private String md5;
}
