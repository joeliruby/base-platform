package com.matariky.commonservice.commondict.constant;

import com.matariky.utils.StringUtils;

import java.io.Serializable;

public class DictTypeKey implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** Reader Configuration **/
    public static final String READER_CONFIG = "EN_READER_CONFIG";
    /** Server Message **/
    public static final String SERVICE_MESSAGE = "_SERVICE_CONSTANT_MESSAGE";
    /** Library Type **/
    public static final String LIBRARY_TYPE = "LIBRARY_TYPE";

    private DictTypeKey() {

    }

    public static String getFullKey(String local, String key) {
        return StringUtils.defaultIfEmpty(local, "EN") + key;
    }

}
