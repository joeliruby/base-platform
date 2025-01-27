package com.matariky.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaveDataIsolation extends BaseDataIsolation {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * User's organization code
     */
    public String operatorOrgCode;

    /**
     * self organization code
     */
    public String operatorSelfOrgCode;

    /**
     * Operationip
     */
    private String operationIp;
}
