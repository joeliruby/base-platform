package com.matariky.commonservice.message.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageVo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Message Content
     */
    private String content;

    /**
     * Message Type
     */
    private Integer msgType;

    /**
     * Object id
     */
    private String target;

    /**
     * Create Time
     */
    private Long createTime;

    /**
     * Wether Read
     */
    private Boolean isRead;
}
