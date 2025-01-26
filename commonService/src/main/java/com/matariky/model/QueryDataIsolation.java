package com.matariky.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collection;


/**
 * @description: Query Data Isolation Objects class
 * @author: bo.chen
 * @create: 2023/9/5 17:01
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryDataIsolation extends BaseDataIsolation implements Serializable {

    /**
	 *
	 */
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
     *  Resource Permission Strategy Code
     */
    @TableField(exist = false)
    private String strategyCode;

    /**
     *  User's organization code
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
