package com.matariky.exception;

import org.springframework.http.HttpStatus;

/**
 * @description: Global Common Exception Class
 * @author: bo.chen
 * @create: 2023/9/6 17:30
 **/
public class QslException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * http status code
     */
    private HttpStatus httpStatus;

    /**
     * Dictionary Message Key
     */
    private String msgKey;

    /**
     *  Parameter 
     */
    private Object[] args;

    public QslException(HttpStatus httpStatus, String msgKey, Object... args) {
	  super(msgKey);
      this.httpStatus = HttpStatus.OK;
      this.msgKey = msgKey;
      this.args = args;
    }

    public QslException( String msgKey) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, msgKey);
    }
    public QslException( String msgKey, Object... args) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, msgKey, args);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public Object[] getArgs() {
        return args;
    }
}
