package com.matariky.jobs.jobsService.assetitm.inventory.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 磁带盘点子 Task 实体类
 * @author: bo.chen
 * @create: 2023/9/22 14:49
 **/
@Data
@TableName("biz_tape_inventory_subtask")
public class TapeInventorySubtask implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Primary Key
     */
    private Long id;

    /**
     * 主 Task id
     */
    private Long taskId;

    /**
     * 盘点 Quantity
     */
    private Integer quantity;

    /**
     * 实际 Quantity
     */
    private Integer actualQuantity;

    /**
     * Wether 有差异：0=否 ,1=是
     */
    private Boolean isDiscrepancy;

    /**
     * Status ：1等待Update,2=无须Update,3=已Update,4=取消
     */
    private Integer status;

    /**
     * Remark
     */
    private String remark;

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
