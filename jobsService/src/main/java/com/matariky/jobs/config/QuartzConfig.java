package com.matariky.jobs.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class QuartzConfig {

    @Bean("quartzScheduler")
    @Primary
    public Scheduler getScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }

}
