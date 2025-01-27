package com.matariky.commonservice.message.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserMessage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Primary Key
     */
    private Long id;

    /**
     * Message id
     */
    private Long messageId;

    /**
     * User id
     */
    private Long userId;

    /**
     * Wether Read
     */
    private Boolean isRead;
}
