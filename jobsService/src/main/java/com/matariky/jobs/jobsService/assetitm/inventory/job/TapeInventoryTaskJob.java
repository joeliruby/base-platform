package com.matariky.jobs.jobsService.assetitm.inventory.job;

import com.matariky.jobs.jobsService.job.base.BaseJob;
import com.matariky.jobs.jobsService.assetitm.inventory.service.TapeInventoryTaskJobService;
import com.matariky.redis.redisson.Lock;
import com.matariky.redis.redisson.LockKey;
import com.matariky.redis.redisson.LockUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TapeInventoryTaskJob implements BaseJob {

    private final static Logger logger = LoggerFactory.getLogger(TapeInventoryTaskJob.class);

    @Autowired
    private TapeInventoryTaskJobService tapeInventoryTaskJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long taskId = Long.parseLong(context.getTrigger().getJobKey().getGroup());
        long startTime = System.currentTimeMillis();
        logger.info("=============== Start Take an inventory Task taskId={}==============", taskId);
        Lock lock = LockUtil.lock(LockKey.TAPE_INVENTORY_TASK_IMMEDIATE_LOCK_KEY + taskId);
        try {
            tapeInventoryTaskJobService.start(taskId);
        } catch (Exception e) {
            logger.error("Take a look at TASK abnormalities,taskId={}", taskId, e);
        } finally {
            lock.unlock();
        }
        logger.info(
                "===============Take a look at task taskid = {} when the coexistence is completed {} seconds==============",
                taskId,
                (System.currentTimeMillis() - startTime) / 1000);
    }
}
