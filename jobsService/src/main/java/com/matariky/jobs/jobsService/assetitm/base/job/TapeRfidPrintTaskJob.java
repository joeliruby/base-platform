package com.matariky.jobs.jobsService.assetitm.base.job;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.jobs.jobsService.job.base.BaseJob;
import com.matariky.jobs.jobsService.assetitm.base.service.TapeRfidPrintTaskJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:  Label  Print 定时 Task 处理
 * @author: bo.chen
 * @create: 2023/9/22 15:02
 **/
public class TapeRfidPrintTaskJob  implements BaseJob {

    private final static Logger logger = LoggerFactory.getLogger(TapeRfidCreateTaskJob.class);

    private static final TapeRfidPrintTaskJobService tapeRfidCreateTaskJobService = SpringContextUtils.getBean(TapeRfidPrintTaskJobService.class);


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long taskId = Long.parseLong(context.getTrigger().getJobKey().getGroup());
        long startTime = System.currentTimeMillis();
        logger.info("===============开始执行 Label  Print  Task taskId={}==============", taskId);
        // Lock lock = LockUtil.lock(LockKey.TAPE_RFIDCREATE_TASK_IMMEDIATE_LOCK_KEY + taskId);
        try {
            tapeRfidCreateTaskJobService.start(taskId);
        } catch (Exception e) {
            logger.error(" Label  Print  Task 异常,taskId={}", taskId, e);
        }
        logger.info("=============== Label  Print  Task taskId={}执行完毕共用时{}秒==============", taskId,  (System.currentTimeMillis() - startTime) / 1000);
    }
}
