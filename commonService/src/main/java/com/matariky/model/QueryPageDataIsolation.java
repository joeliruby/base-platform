package com.matariky.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.commons.lang3.math.NumberUtils;


import java.io.Serializable;
import java.util.Objects;

/**
 * @description: Pagination  Query Data Isolation Objects class
 * @author: bo.chen
 * @create: 2023/9/6 10:16
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryPageDataIsolation extends  QueryDataIsolation implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Current page number  for pagination
     */
    private Integer index;

    /**
     * Quantity per page for pagination
     */
    private Integer perPage;

    private String orderField;

    private String order;

    public Integer getIndex() {
        return Objects.isNull(index) ? NumberUtils.INTEGER_ONE : index;
    }

    public Integer getPerPage() {
        return Objects.isNull(perPage) ? 20 : perPage;
    }

    public Integer getPageStart() {
        return (getIndex() - NumberUtils.INTEGER_ONE) * getPageSize();
    }

    public Integer getPageSize() {
        return getPerPage();
    }
}
