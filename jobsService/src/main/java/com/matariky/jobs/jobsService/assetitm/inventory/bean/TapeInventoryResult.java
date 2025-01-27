package com.matariky.jobs.jobsService.assetitm.inventory.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@TableName(value = "biz_tape_inventory_result", autoResultMap = true)
@Data
public class TapeInventoryResult implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Primary Key
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * inventory Task id
     */
    private Long taskId;

    /**
     * Sub Task id
     */
    private Long subtaskId;

    /**
     * Label epc
     */
    private String epc;

    /**
     * tid
     */
    private String tid;

    /**
     * Label id
     */
    private Long labelId;

    /**
     * Library id
     */
    private String locationId;

    /**
     * Library id
     */
    private Long libraryId;

    /**
     * Reader Code
     */
    private String readerCode;

    /**
     * Error Reason
     */
    private String failReason;

    /**
     * Result: 1 = loss, 2 = new discovery, 3 = normal
     */
    private Integer result;

    /**
     * File path
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> filePaths;

    /**
     * Abnormal Label Wether effective,0=No ,1=Yes
     */
    private Boolean isValid;

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
