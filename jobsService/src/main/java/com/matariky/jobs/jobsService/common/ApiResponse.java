package com.matariky.jobs.jobsService.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * <p>
 * Universal API packaging
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.common
 * @description: Universal API packaging
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
     * Return data
     */
    private Object data;

    public ApiResponse() {
    }

    private ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    /**
     * General encapsulation to retrieve ApiResponse object
     *
     * @param message Return message
     * @param data    Return data
     * @return ApiResponse
     */
    public static ApiResponse of(String message, Object data) {
        return new ApiResponse(message, data);
    }

    /**
     * General success encapsulation to retrieve ApiResponse object
     *
     * @param data Return data
     * @return ApiResponse
     */
    public static ApiResponse ok(Object data) {
        return new ApiResponse(HttpStatus.OK.getReasonPhrase(), data);
    }

    /**
     * General encapsulation to retrieve ApiResponse object
     *
     * @param message Return message
     * @return ApiResponse
     */
    public static ApiResponse msg(String message) {
        return of(message, null);
    }
}
