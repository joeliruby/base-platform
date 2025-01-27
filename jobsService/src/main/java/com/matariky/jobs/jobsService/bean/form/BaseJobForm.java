package com.matariky.jobs.jobsService.bean.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseJobForm implements Serializable {
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
     * Task Type ,1=Execute immediately ,2=One -time,3=cycle
     */
    private Integer taskType;

    private String cronExpression;

    private String jobClassName;

    private String jobGroupName;
}
