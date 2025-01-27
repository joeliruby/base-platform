package com.matariky.jobs.jobsService.assetitm.base.job;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.jobs.jobsService.job.base.BaseJob;
import com.matariky.jobs.jobsService.assetitm.base.service.TapeRfidCreateTaskJobService;
import com.matariky.redis.redisson.Lock;
import com.matariky.redis.redisson.LockKey;
import com.matariky.redis.redisson.LockUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: Label 生产 Scheduled Task 处理
 * @author: bo.chen
 * @create: 2023/9/22 15:02
 **/
public class TapeRfidCreateTaskJob implements BaseJob {

    private final static Logger logger = LoggerFactory.getLogger(TapeRfidCreateTaskJob.class);

    private static final TapeRfidCreateTaskJobService tapeRfidCreateTaskJobService = SpringContextUtils
            .getBean(TapeRfidCreateTaskJobService.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long taskId = Long.parseLong(context.getTrigger().getJobKey().getGroup());
        long startTime = System.currentTimeMillis();
        logger.info("=============== Start 执行 Label  Generation  Task taskId={}==============", taskId);
        // Lock lock = LockUtil.lock(LockKey.TAPE_RFIDCREATE_TASK_IMMEDIATE_LOCK_KEY +
        // taskId);
        try {
            tapeRfidCreateTaskJobService.start(taskId);
        } catch (Exception e) {
            logger.error(" Label  Generation  Task 异常,taskId={}", taskId, e);
        }
        logger.info("=============== Label  Generation  Task taskId={}执行完毕共用时{}秒==============", taskId,
                (System.currentTimeMillis() - startTime) / 1000);
    }
}
