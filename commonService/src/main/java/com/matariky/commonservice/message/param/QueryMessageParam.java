package com.matariky.commonservice.message.param;

import com.matariky.model.QueryPageDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryMessageParam extends QueryPageDataIsolation {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Wether Read
     *
     */
    private Boolean isRead;
}
