package com.matariky.commonservice.message.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户消息ov
 * @author: bo.chen
 * @create: 2023/10/19 10:33
 **/
@Data
public class MessageVo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 消息内容
     */
    private String content;

    /**
     * 消息Type 
     */
    private Integer msgType;

    /**
     * 对象id
     */
    private String target;

    /**
     * 创建 Time 
     */
    private Long createTime;

    /**
     *  Wether 已读
     */
    private Boolean isRead;
}
