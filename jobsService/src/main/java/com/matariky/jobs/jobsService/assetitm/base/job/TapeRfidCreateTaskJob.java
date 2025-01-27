package com.matariky.jobs.jobsService.assetitm.base.job;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.jobs.jobsService.job.base.BaseJob;
import com.matariky.jobs.jobsService.assetitm.base.service.TapeRfidCreateTaskJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TapeRfidCreateTaskJob implements BaseJob {

    private final static Logger logger = LoggerFactory.getLogger(TapeRfidCreateTaskJob.class);

    private static final TapeRfidCreateTaskJobService tapeRfidCreateTaskJobService = SpringContextUtils
            .getBean(TapeRfidCreateTaskJobService.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long taskId = Long.parseLong(context.getTrigger().getJobKey().getGroup());
        long startTime = System.currentTimeMillis();
        logger.info("=============== Start implement Label  Generation  Task taskId={}==============", taskId);
        // Lock lock = LockUtil.lock(LockKey.TAPE_RFIDCREATE_TASK_IMMEDIATE_LOCK_KEY +
        // taskId);
        try {
            tapeRfidCreateTaskJobService.start(taskId);
        } catch (Exception e) {
            logger.error(" Label  Generation  Task abnormal,taskId={}", taskId, e);
        }
        logger.info(
                "=============== Label  Generation  Task taskId={}{} Seconds when the implementation is completed==============",
                taskId,
                (System.currentTimeMillis() - startTime) / 1000);
    }
}
