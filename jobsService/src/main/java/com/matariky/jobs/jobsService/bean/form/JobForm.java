package com.matariky.jobs.jobsService.bean.form;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * <p>
 * 定时 Task 详情
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.entity.form
 * @description: 定时 Task 详情
 * @version: V1.0
 */
@Data
@Accessors(chain = true)
public class JobForm {
	
	
    /**
     * 定时 Task 全类名
     */
//    @NotBlank(message = "类名不能为空")
    private String jobClassName;
	
	
    /**
     *  Task 组名
     */
//    @NotBlank(message = " Task 组名不能为空")
    private String jobGroupName;
	
	
    /**
     * 定时 Task cron表达式
     */
//    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;
	
	private String tenantId;
}
