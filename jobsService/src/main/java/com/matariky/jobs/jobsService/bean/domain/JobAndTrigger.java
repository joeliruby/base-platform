package com.matariky.jobs.jobsService.bean.domain;

import lombok.Data;

import java.math.BigInteger;

/**
 * <p>
 * Physical class
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.entity.domain
 * @description: Physical class
 * @version: V1.0
 */
@Data
public class JobAndTrigger {
    /**
     * Scheduled Task Name
     */
    private String jobName;
    /**
     * Scheduled Task Group
     */
    private String jobGroup;
    /**
     * Scheduled Task full class name
     */
    private String jobClassName;
    /**
     * Trigger name
     */
    private String triggerName;
    /**
     * Trigger group
     */
    private String triggerGroup;
    /**
     * Repeat interval
     */
    private BigInteger repeatInterval;
    /**
     * Times triggered
     */
    private BigInteger timesTriggered;
    /**
     * Cron expression
     */
    private String cronExpression;
    /**
     * Time zone
     */
    private String timeZoneId;
    /**
     * Scheduled Task Status
     */
    private String triggerState;
}