package com.matariky.jobs.jobsService.bean.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 盘点 Task 
 * @author: bo.chen
 * @create: 2023/10/16 18:14
 **/
@Data
public class InventoryJobForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *  Task id
     */
    private Long taskId;

    /**
     * 开始 Time 
     */
    private Long startTime;

    /**
     * 结束 Time 
     */
    private Long endTime;

    /**
     *  Task Type ，1=立即执行，2=一次性,3=周期
     */
    private Integer taskType;

    /**
     *  Time 间隔
     */
    private Integer timeInterval;

    /**
     * 间隔单位
     */
    private Integer intervalUnit;
}
