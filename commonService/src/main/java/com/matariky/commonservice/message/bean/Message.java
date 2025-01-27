package com.matariky.commonservice.message.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("common_message")
public class Message implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Primary Key
     */
    private Long id;

    /**
     * Operator Organization Code
     */
    private String operatorOrgCode;

    /**
     * Operator Self Organization Code
     */
    private String operatorSelfOrgCode;

    /**
     * Tenant Code
     */
    private String tenantId;

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
     * Creater
     */
    private Long createdBy;

    /**
     * Updater
     */
    private Long updatedBy;

    /**
     * Create Time
     */
    private Long createTime;

    /**
     * Update Time
     */
    private Long updateTime;

    /**
     * Delete Time
     */
    private Long deleteTime;
}
