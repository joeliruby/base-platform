package com.matariky.orderservice.param;

import com.matariky.model.QueryPageDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class QueryTapeOrderSuiteParam extends QueryPageDataIsolation {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     *  Wether 分页
     */
    private boolean isPage = Boolean.FALSE;


    private String suiteName;
    private String suiteStatus;
}
