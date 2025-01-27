package com.matariky.jobs.jobsService.bean.form;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Scheduled Task Detail
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.entity.form
 * @description: Scheduled Task Detail
 * @version: V1.0
 */
@Data
@Accessors(chain = true)
public class JobForm {

    /**
     * Scheduled Task Full class Name
     */
    // @NotBlank(message = "Class Name cannot be empty")
    private String jobClassName;

    /**
     * Task Group Name
     */
    // @NotBlank(message = " Task Group name cannot be empty")
    private String jobGroupName;

    /**
     * Scheduled Task cron expression
     */
    // @NotBlank(message = "cron The expression cannot be empty")
    private String cronExpression;

    private String tenantId;
}
