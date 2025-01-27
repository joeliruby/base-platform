package com.matariky.jobs.jobsService.bean.domain;

import lombok.Data;

import java.math.BigInteger;

/**
 * <p>
 * 实体类
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.entity.domain
 * @description: 实体类
 * @version: V1.0
 */
@Data
public class JobAndTrigger {
    /**
     * Scheduled Task Name
     */
    private String jobName;
    /**
     * Scheduled Task 组
     */
    private String jobGroup;
    /**
     * Scheduled Task 全类 Name
     */
    private String jobClassName;
    /**
     * 触发器 Name
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * 重复间隔
     */
    private BigInteger repeatInterval;
    /**
     * 触发次数
     */
    private BigInteger timesTriggered;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 时区
     */
    private String timeZoneId;
    /**
     * Scheduled Task Status
     */
    private String triggerState;
}