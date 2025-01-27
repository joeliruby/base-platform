package com.matariky.utils;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /** Status 码 */
    public static final String CODE_TAG = "code";

    /** 返回 Content */
    public static final String MSG_TAG = "message";

    /** Data Object */
    public static final String DATA_TAG = "data";

    public static final String SUCCESS = "success";

    public static final String FAILURE = "failure";

    /**
     * 初始化 one 新Create 的 AjaxResult Object ,使其表示 one 空 Message 。
     */
    public AjaxResult() {
    }

    /**
     * 初始化 one 新Create 的 AjaxResult Object
     *
     * @param code Status 码
     * @param msg  返回 Content
     */
    public AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化 one 新Create 的 AjaxResult Object
     *
     * @param code Status 码
     * @param msg  返回 Content
     * @param data Data Object
     */
    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    public AjaxResult(int code, String msg, Page data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", data.getResult());
            map.put("total", data.getTotal());
            super.put(DATA_TAG, map);
        }
    }

    /**
     * 返回成功 Message
     *
     * @return 成功 Message
     */
    public static AjaxResult success() {
        return AjaxResult.success(" Operation成功");
    }

    /**
     * 返回成功 Data
     *
     * @return 成功 Message
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success(" Operation成功", data);
    }

    /**
     * 返回成功 Message
     *
     * @param msg 返回 Content
     * @return 成功 Message
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功 Message
     *
     * @param msg  返回 Content
     * @param data Data Object
     * @return 成功 Message
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回 Error Message
     *
     * @return
     */
    public static AjaxResult error() {
        return AjaxResult.error(" OperationFailed");
    }

    /**
     * 返回 Error Message
     *
     * @param msg 返回 Content
     * @return 警告 Message
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回 Error Message
     *
     * @param msg  返回 Content
     * @param data Data Object
     * @return 警告 Message
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回 Error Message
     *
     * @param code Status 码
     * @param msg  返回 Content
     * @return 警告 Message
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }

    /**
     * 方便链式调用
     *
     * @param key   键
     * @param value 值
     * @return Data Object
     */
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
