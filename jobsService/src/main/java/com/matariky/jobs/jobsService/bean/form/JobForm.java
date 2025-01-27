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
     * Scheduled Task 全类 Name
     */
    // @NotBlank(message = "类 Name 不能为空")
    private String jobClassName;

    /**
     * Task 组 Name
     */
    // @NotBlank(message = " Task 组 Name 不能为空")
    private String jobGroupName;

    /**
     * Scheduled Task cron表达式
     */
    // @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    private String tenantId;
}
