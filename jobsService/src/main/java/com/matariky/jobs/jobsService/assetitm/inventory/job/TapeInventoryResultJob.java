package com.matariky.jobs.jobsService.assetitm.inventory.job;

import com.matariky.jobs.jobsService.job.base.BaseJob;
import com.matariky.jobs.jobsService.assetitm.inventory.service.TapeInventoryTaskJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 盘点 Task 结果处理 Task
 * @author: bo.chen
 * @create: 2023/9/22 9:51
 **/
public class TapeInventoryResultJob implements BaseJob {

    private final static Logger logger = LoggerFactory.getLogger(TapeInventoryResultJob.class);

    @Autowired
    private TapeInventoryTaskJobService tapeInventoryTaskJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long subtaskId = Long.parseLong(context.getTrigger().getJobKey().getGroup());
        long startTime = System.currentTimeMillis();
        logger.info("=============== Start 执行盘点结果处理 Task subtaskId={}==============", subtaskId);
        try {
            tapeInventoryTaskJobService.end(subtaskId);
        } catch (Exception e) {
            logger.error("盘点结果处理 Task 异常,subtaskId={}", subtaskId, e);
        }
        logger.info("===============盘点结果处理 Task subtaskId={}执行完毕共用时{}秒==============", subtaskId,
                (System.currentTimeMillis() - startTime) / 1000);
    }
}
