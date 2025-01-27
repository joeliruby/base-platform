package com.matariky.jobs.jobsService.assetitm.inventory.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@TableName(value = "biz_tape_inventory_task", autoResultMap = true)
@Data
public class TapeInventoryTask implements Serializable {

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
     * Serial Number
     */
    private String serialNumber;

    /**
     * Task Name
     */
    private String taskName;

    /**
     * Task Type ,1=Execute immediately, 2 = one -time, 3 = cycle
     */
    private Integer taskType;

    /**
     * App 1=PC,2=APP
     */
    private Integer application;

    /**
     * Library id
     */
    private String locationId;

    /**
     * Library Id gather
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> libraryIds;

    /**
     * Time interval
     */
    private Integer timeInterval;

    /**
     * Interval unit
     */
    private Integer intervalUnit;

    /**
     * inventory Start Time
     */
    private Long startTime;

    /**
     * Inventory end Time
     */
    private Long endTime;

    /**
     * inventory Quantity
     */
    private Integer quantity;

    /**
     * Actual Quantity
     */
    private Integer actualQuantity;

    /**
     * Status: 1 = Activate, 2 = Deactivate
     */
    private Integer status;

    /**
     * Process Status: 1 = To Be Started, 2 = Scanning, 3 = Waiting for Update, 4 =
     * Task Ended
     */
    private Integer processStatus;

    /**
     * Whether there is a discrepancy: 0 = No, 1 = Yes
     */
    private Boolean isDiscrepancy;

    /**
     * Last Subtask ID
     */
    private Long lastSubtaskId;

    /**
     * Operation IP
     */
    private String operationIp;

    /**
     * Remark
     */
    private String remark;

    /**
     * Localization Identifier
     */
    private String local;

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
