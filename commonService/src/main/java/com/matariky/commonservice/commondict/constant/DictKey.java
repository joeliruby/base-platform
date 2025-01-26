package com.matariky.commonservice.commondict.constant;

import java.io.Serializable;

/**
 * @description: 字典键常量类
 * @author: bo.chen
 * @create: 2023/9/13 14:53
 **/
public class DictKey implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 读写器最大读取 Time  **/
    public static final String READER_MAX_TIMES = "READER_MAX_TIMES";

    /**
     * 系统异常
     */
    public static final String SYSTEM_ERROR = "SYSTEM_ERROR";

    private DictKey() {

    }
}
