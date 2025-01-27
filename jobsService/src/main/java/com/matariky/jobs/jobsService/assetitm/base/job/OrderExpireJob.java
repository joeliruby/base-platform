package com.matariky.jobs.jobsService.assetitm.base.job;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.jobs.jobsService.job.base.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Order expire JOB
 */
public class OrderExpireJob implements BaseJob {

    private static final JdbcTemplate jdbcTemplate = SpringContextUtils.getBean(JdbcTemplate.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long currentTime = System.currentTimeMillis();
        int update = jdbcTemplate.update("update  basic_rfid_platform.order_info set order_status = 3 where "
                + currentTime + "  > expiration_end_time  and  order_status = 1 ");
        System.out.println("Order excessive number： " + update);
    }
}
