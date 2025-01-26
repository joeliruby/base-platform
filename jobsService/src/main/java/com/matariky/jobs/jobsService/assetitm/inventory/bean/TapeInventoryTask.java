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
     * 主键
     */
    private Long id;

    /**
     * 操作人机构编码
     */
    private String operatorOrgCode;

    /**
     * 操作人自己机构编码
     */
    private String operatorSelfOrgCode;

    /**
     *  Tenant 编码
     */
    private String tenantId;

    /**
     * 序列号
     */
    private String serialNumber;

    /**
     *  Task  Name
     */
    private String taskName;

    /**
     *  Task Type ，1=立即执行，2=一次性,3=周期
     */
    private Integer taskType;

    /**
     * 应用 1=PC,2=APP
     */
    private Integer application;

    /**
     * 机架id
     */
    private String locationId;

    /**
     * 机架Id集合
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> libraryIds;

    /**
     *  Time 间隔
     */
    private Integer timeInterval;

    /**
     * 间隔单位
     */
    private Integer intervalUnit;

    /**
     * 盘点开始 Time 
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
     *  Status :1=启用，2=停用
     */
    private Integer status;

    /**
     * 流程 Status ：流程 Status ：1= To Be Started ，2=扫描中，3=等待Update，4= Task 结束
     */
    private Integer processStatus;

    /**
     *  Wether 有差异：0=否，1=是
     */
    private Boolean isDiscrepancy;

    /**
     * 最后子 Task id
     */
    private Long lastSubtaskId;

    /**
     * 操作ip
     */
    private String operationIp;

    /**
     *  Remark 
     */
    private String remark;

    /**
     * 国际化标识
     */
    private String local;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 修改人
     */
    private Long updatedBy;

    /**
     * 创建 Time 
     */
    private Long createTime;

    /**
     * 修改 Time 
     */
    private Long updateTime;

    /**
     * 删除 Time 
     */
    private Long deleteTime;
}
