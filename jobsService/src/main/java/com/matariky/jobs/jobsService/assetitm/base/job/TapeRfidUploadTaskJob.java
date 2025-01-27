package com.matariky.jobs.jobsService.assetitm.base.job;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.jobs.jobsService.job.base.BaseJob;
import com.matariky.jobs.jobsService.assetitm.base.service.TapeRfidUploadTaskJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: Label Import Scheduled Task 处理
 * @author: bo.chen
 * @create: 2023/9/22 15:02
 **/
public class TapeRfidUploadTaskJob implements BaseJob {

    private final static Logger logger = LoggerFactory.getLogger(TapeRfidUploadTaskJob.class);

    private static final TapeRfidUploadTaskJobService tapeRfidUploadTaskJobService = SpringContextUtils
            .getBean(TapeRfidUploadTaskJobService.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long taskId = Long.parseLong(context.getTrigger().getJobKey().getGroup());
        long startTime = System.currentTimeMillis();
        logger.info("=============== Start 执行 Label  Print ImporttaskId={}==============", taskId);
        // Lock lock = LockUtil.lock(LockKey.TAPE_RFIDCREATE_TASK_IMMEDIATE_LOCK_KEY +
        // taskId);
        try {
            tapeRfidUploadTaskJobService.start(taskId);
        } catch (Exception e) {
            logger.error(" Label Import Task 异常,taskId={}", taskId, e);
        }
        logger.info("=============== Label Import Task taskId={}执行完毕共用时{}秒==============", taskId,
                (System.currentTimeMillis() - startTime) / 1000);
    }
}
