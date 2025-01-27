package com.matariky.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryDataIsolation extends BaseDataIsolation {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * Shared Resource Self Org Codes
     */
    @TableField(exist = false)
    private Collection<String> sharingSelfOrgCodes;

    /**
     * Shared Resource Org Codes
     */
    @TableField(exist = false)
    private Collection<String> sharingOrgCodes;

    /**
     * Resource Permission Strategy Code
     */
    @TableField(exist = false)
    private String strategyCode;

    /**
     * User's organization code
     */
    @TableField(exist = false)
    private String orgCode;

    /**
     * self organization code
     */
    @TableField(exist = false)
    private String selfOrgCode;

    @TableField(exist = false)
    private String operatorSelfOrgCode;

}
