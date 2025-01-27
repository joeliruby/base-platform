package com.matariky.jobs.jobsService.assetitm.base.job;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.jobs.jobsService.job.base.BaseJob;
import com.matariky.jobs.jobsService.assetitm.base.service.TapeRfidUploadTaskJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TapeRfidUploadTaskJob implements BaseJob {

    private final static Logger logger = LoggerFactory.getLogger(TapeRfidUploadTaskJob.class);

    private static final TapeRfidUploadTaskJobService tapeRfidUploadTaskJobService = SpringContextUtils
            .getBean(TapeRfidUploadTaskJobService.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long taskId = Long.parseLong(context.getTrigger().getJobKey().getGroup());
        long startTime = System.currentTimeMillis();
        logger.info("=============== Start implement Label  Print ImporttaskId={}==============", taskId);
        // Lock lock = LockUtil.lock(LockKey.TAPE_RFIDCREATE_TASK_IMMEDIATE_LOCK_KEY +
        // taskId);
        try {
            tapeRfidUploadTaskJobService.start(taskId);
        } catch (Exception e) {
            logger.error(" Label Import Task abnormal,taskId={}", taskId, e);
        }
        logger.info(
                "=============== Label Import Task taskId={}When the execution is completed and shared{}Second==============",
                taskId,
                (System.currentTimeMillis() - startTime) / 1000);
    }
}
