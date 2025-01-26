package com.matariky.commonservice.base.bean;

import com.matariky.model.QueryDataIsolation;
import lombok.Data;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class BasicBaseFormConfig extends QueryDataIsolation {

    private Long id;
    private String name;
    private String fieldName;
    private String fieldMap;
    private String fieldType;
    private String remark;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;
    private Long createBy;
    private Long updateBy;
    private String operatorOrgCode;
    private String operatorSelfOrgCode;
    private String tenantId;
    private Boolean isRequired;
}
