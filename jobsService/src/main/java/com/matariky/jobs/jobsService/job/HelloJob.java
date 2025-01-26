package com.matariky.jobs.jobsService.job;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import com.matariky.jobs.jobsService.job.base.BaseJob;

import org.quartz.JobExecutionContext;

/**
 * <p>
 * Hello Job
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.job
 * @description: Hello Job
 * @version: V1.0
 */
@Slf4j
public class HelloJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext context) {
        log.error("Hello Job 执行 Time : {}", DateUtil.now());
    }
}