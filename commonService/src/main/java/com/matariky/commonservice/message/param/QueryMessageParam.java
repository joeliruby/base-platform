package com.matariky.commonservice.message.param;

import com.matariky.model.QueryPageDataIsolation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:  Query 消息 Parameter 
 * @author: bo.chen
 * @create: 2023/11/16 10:20
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryMessageParam extends QueryPageDataIsolation {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     *  Wether 已读
     *
     */
    private Boolean isRead;
}
