/**
 * ClassName: BywObject
 * CopyRight: Totoro
 * Date: 2014/9/24
 * Version: 1.0
 */
package com.matariky.model;


import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public abstract class BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
	
	public abstract String queryByDynamicCondition(Map<String, String> queryMap);
}
