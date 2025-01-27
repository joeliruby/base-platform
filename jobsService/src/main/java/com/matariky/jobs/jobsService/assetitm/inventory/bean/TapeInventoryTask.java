package com.matariky.jobs.jobsService.assetitm.inventory.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 磁带盘点 Task bean
 * @author: bo.chen
 * @create: 2023/9/08 9:48
 **/
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
     * 序列号
     */
    private String serialNumber;

    /**
     * Task Name
     */
    private String taskName;

    /**
     * Task Type ,1=立即执行 ,2=一次性,3=周期
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
     * Library Id集合
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> libraryIds;

    /**
     * Time 间隔
     */
    private Integer timeInterval;

    /**
     * 间隔单位
     */
    private Integer intervalUnit;

    /**
     * 盘点 Start Time
     */
    private Long startTime;

    /**
     * 盘点结束 Time
     */
    private Long endTime;

    /**
     * 盘点 Quantity
     */
    private Integer quantity;

    /**
     * 实际 Quantity
     */
    private Integer actualQuantity;

    /**
     * Status :1= Activate ,2= Deactivate
     */
    private Integer status;

    /**
     * 流程 Status ：流程 Status ：1= To Be Started ,2=扫描中 ,3=等待Update ,4= Task 结束
     */
    private Integer processStatus;

    /**
     * Wether 有差异：0=否 ,1=是
     */
    private Boolean isDiscrepancy;

    /**
     * 最后子 Task id
     */
    private Long lastSubtaskId;

    /**
     * Operationip
     */
    private String operationIp;

    /**
     * Remark
     */
    private String remark;

    /**
     * 国际化标识
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
