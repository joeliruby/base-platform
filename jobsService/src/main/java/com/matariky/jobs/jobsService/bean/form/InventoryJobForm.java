package com.matariky.jobs.jobsService.bean.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryJobForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Task id
     */
    private Long taskId;

    /**
     * Start Time
     */
    private Long startTime;

    /**
     * Finish Time
     */
    private Long endTime;

    /**
     * Task Type ,1=Execute immediately ,2=One -time,3=cycle
     */
    private Integer taskType;

    /**
     * Time interval
     */
    private Integer timeInterval;

    /**
     * Interval unit
     */
    private Integer intervalUnit;
}
