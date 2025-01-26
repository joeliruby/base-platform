package com.matariky.jobs.jobsService.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * <p>
 * 通用Api封装
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.common
 * @description: 通用Api封装
 * @version: V1.0
 */
@Data
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 1L;

	/**
     * 返回 Information 
     */
    private String message;

    /**
     * 返回 Data 
     */
    private Object data;

    public ApiResponse() {
    }

    private ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    /**
     * 通用封装获取ApiResponse对象
     *
     * @param message 返回 Information 
     * @param data    返回 Data 
     * @return ApiResponse
     */
    public static ApiResponse of(String message, Object data) {
        return new ApiResponse(message, data);
    }

    /**
     * 通用成功封装获取ApiResponse对象
     *
     * @param data 返回 Data 
     * @return ApiResponse
     */
    public static ApiResponse ok(Object data) {
        return new ApiResponse(HttpStatus.OK.getReasonPhrase(), data);
    }

    /**
     * 通用封装获取ApiResponse对象
     *
     * @param message 返回 Information 
     * @return ApiResponse
     */
    public static ApiResponse msg(String message) {
        return of(message, null);
    }

}
