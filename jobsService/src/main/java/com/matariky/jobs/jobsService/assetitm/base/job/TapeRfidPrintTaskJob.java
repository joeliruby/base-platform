package com.matariky.jobs.jobsService.assetitm.base.job;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.jobs.jobsService.job.base.BaseJob;
import com.matariky.jobs.jobsService.assetitm.base.service.TapeRfidPrintTaskJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TapeRfidPrintTaskJob implements BaseJob {

    private final static Logger logger = LoggerFactory.getLogger(TapeRfidCreateTaskJob.class);

    private static final TapeRfidPrintTaskJobService tapeRfidCreateTaskJobService = SpringContextUtils
            .getBean(TapeRfidPrintTaskJobService.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long taskId = Long.parseLong(context.getTrigger().getJobKey().getGroup());
        long startTime = System.currentTimeMillis();
        logger.info("=============== Start implement Label  Print  Task taskId={}==============", taskId);
        // Lock lock = LockUtil.lock(LockKey.TAPE_RFIDCREATE_TASK_IMMEDIATE_LOCK_KEY +
        // taskId);
        try {
            tapeRfidCreateTaskJobService.start(taskId);
        } catch (Exception e) {
            logger.error(" Label  Print  Task abnormal,taskId={}", taskId, e);
        }
        logger.info(
                "=============== Label  Print  Task taskId={}When the execution is completed and shared{}Second==============",
                taskId,
                (System.currentTimeMillis() - startTime) / 1000);
    }
}
