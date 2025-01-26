package com.matariky.commonservice.upload.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应 Data 
 *
 * @since 1.0.0
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 编码：0表示成功，其他值表示Failed
     */
    private int code = 0;
    /**
     * 消息内容
     */
    private String message = "success";
    /**
     * 响应 Data 
     */
    private T data;
    
    public Result<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public boolean success(){
        return code == 0 ? true : false;
    }
    
    public Result<T> error(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

  
}
