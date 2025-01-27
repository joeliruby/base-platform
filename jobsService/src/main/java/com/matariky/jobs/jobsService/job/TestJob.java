package com.matariky.jobs.jobsService.job;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import com.matariky.jobs.jobsService.job.base.BaseJob;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * <p>
 * Test Job
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.job
 * @version: V1.0
 */
@Slf4j
public class TestJob implements BaseJob {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Update过期订单 Status   Start 执行 Time :{}", DateUtil.now());
        Date date2 = com.matariky.utils.DateUtil.getEndTimeOfDay(new Date());
        String sql1 = "update order_info as t set t.order_status = '3' where t.delete_time = 0 and t.order_status != '3' and t.expiration_end_time <= "
                + date2.getTime();

        String sql2 = "update user_user set order_code = '' where order_code in (select o.order_code from order_info as o where o.order_status = '3')";

        int count = jdbcTemplate.update(sql1);

        int count2 = jdbcTemplate.update(sql2);
        log.info("Update过期订单 Status  结束执行 Time :{}, Update Quantity：{}:{}", DateUtil.now(), count, count2);
    }
}