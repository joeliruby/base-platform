package com.matariky.commonservice.commondict.constant;

import com.matariky.utils.StringUtils;

import java.io.Serializable;

/**
 * @description: 字典Type key
 * @author: bo.chen
 * @create: 2023/9/13 14:51
 **/
public class DictTypeKey implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 读写器配置项 **/
    public static final String READER_CONFIG = "EN_READER_CONFIG";
    /** 服务器消息 **/
    public static final String SERVICE_MESSAGE = "_SERVICE_CONSTANT_MESSAGE";
    /** 机架Type  **/
    public static final String LIBRARY_TYPE = "LIBRARY_TYPE";

    private DictTypeKey() {

    }


    /**
     * @Description: 获取全称key
     * @Author: bo.chen
     * @Date: 2023/10/16 10:37
     * @param local
     * @param key
     * @return java.lang.String
     **/
    public static String getFullKey(String local, String key) {
        return StringUtils.defaultIfEmpty(local, "EN") + key;
    }

}
