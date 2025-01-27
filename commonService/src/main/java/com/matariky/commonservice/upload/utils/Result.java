package com.matariky.commonservice.upload.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * response Data
 *
 * @since 1.0.0
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Code ：0Represents success, other values ​​indicateFailed
     */
    private int code = 0;
    /**
     * Message Content
     */
    private String message = "success";
    /**
     * response Data
     */
    private T data;

    public Result<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public boolean success() {
        return code == 0 ? true : false;
    }

    public Result<T> error(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

}
