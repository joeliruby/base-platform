package com.matariky.jobs.jobsService.assetitm.inventory.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

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
     * host Task id
     */
    private Long taskId;

    /**
     * inventory Quantity
     */
    private Integer quantity;

    /**
     * actual Quantity
     */
    private Integer actualQuantity;

    /**
     * Wether Different：0=No ,1=Yes
     */
    private Boolean isDiscrepancy;

    /**
     * Status ：1wait Update,2=No need to Update,3= Updated,4=Cancel
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
