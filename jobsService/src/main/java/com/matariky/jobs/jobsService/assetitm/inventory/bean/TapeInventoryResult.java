package com.matariky.jobs.jobsService.assetitm.inventory.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 磁带盘点结果实体类
 * @author: bo.chen
 * @create: 2023/9/08 10:27
 **/
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
     * 盘点 Task id
     */
    private Long taskId;

    /**
     * 子 Task id
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
     * Error 原因
     */
    private String failReason;

    /**
     * 结果:1=丢失 ,2=新发现 ,3=正常
     */
    private Integer result;

    /**
     * 文件路径
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> filePaths;

    /**
     * 非正常 Label Wether 有效,0=否 ,1=是
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
