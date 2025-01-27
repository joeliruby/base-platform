package com.matariky.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @description: Save Data Isolation Objects class
 * @author: bo.chen
 * @create: 2023/9/5 17:01
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class SaveDataIsolation extends BaseDataIsolation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *  User's organization code
     */
    public String operatorOrgCode;

    /**
     * self organization code
     */
    public String operatorSelfOrgCode;

    /**
     *  Operationip
     */
    private String operationIp;
}
