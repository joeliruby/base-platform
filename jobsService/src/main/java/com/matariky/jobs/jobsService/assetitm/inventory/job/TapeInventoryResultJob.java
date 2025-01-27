package com.matariky.jobs.jobsService.assetitm.inventory.job;

import com.matariky.jobs.jobsService.job.base.BaseJob;
import com.matariky.jobs.jobsService.assetitm.inventory.service.TapeInventoryTaskJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TapeInventoryResultJob implements BaseJob {

    private final static Logger logger = LoggerFactory.getLogger(TapeInventoryResultJob.class);

    @Autowired
    private TapeInventoryTaskJobService tapeInventoryTaskJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long subtaskId = Long.parseLong(context.getTrigger().getJobKey().getGroup());
        long startTime = System.currentTimeMillis();
        logger.info("=============== Start Execute results processing Task subtaskId={}==============", subtaskId);
        try {
            tapeInventoryTaskJobService.end(subtaskId);
        } catch (Exception e) {
            logger.error("Inventory results handle task abnormality,subtaskId={}", subtaskId, e);
        }
        logger.info(
                "===============Inventory results Task subtaskId={}{} Seconds when the implementation is completed==============",
                subtaskId,
                (System.currentTimeMillis() - startTime) / 1000);
    }
}
