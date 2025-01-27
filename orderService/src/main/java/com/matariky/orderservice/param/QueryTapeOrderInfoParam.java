package com.matariky.orderservice.param;

import com.matariky.model.QueryPageDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryTapeOrderInfoParam extends QueryPageDataIsolation {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Wether Pagination
     */
    private boolean isPage = Boolean.FALSE;

    private String tenantName;
    private String orderCode;
    private String suiteName;
    private String startTime;
    private String endTime;
    private String orderStatus;
    private String orderTenantId;
}
