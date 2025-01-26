/**
 * Copyright (c) 2007 Beijing Shiji Kunlun Software Co., Ltd. All Rights Reserved.
 * $Id$
 */
package com.matariky.id;

/**
 * @description: id Generation 器异常类
 * @author: bo.chen
 * @create: 2023/10/9 16:19
 **/
public class UidGenerateException extends RuntimeException {

    private static final long serialVersionUID = -3490134604963231996L;

    /**
     * Default constructor
     */
    public UidGenerateException() {

        super();
    }

    /**
     * Constructor with message & cause
     *
     * @param message
     * @param cause
     */
    public UidGenerateException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public UidGenerateException(String message) {

        super(message);
    }

    /**
     * Constructor with message format
     *
     * @param msgFormat
     * @param args
     */
    public UidGenerateException(String msgFormat, Object... args) {

        super(String.format(msgFormat, args));
    }

    /**
     * Constructor with cause
     *
     * @param cause
     */
    public UidGenerateException(Throwable cause) {

        super(cause);
    }
}