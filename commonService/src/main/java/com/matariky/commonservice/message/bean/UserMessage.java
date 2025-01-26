package com.matariky.commonservice.message.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户消息表
 * @author: bo.chen
 * @create: 2023/11/16 9:50
 **/
@Data
public class UserMessage implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Long id;

    /**
     * 消息id
     */
    private Long messageId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     *  Wether 已读
     */
    private Boolean isRead;
}
