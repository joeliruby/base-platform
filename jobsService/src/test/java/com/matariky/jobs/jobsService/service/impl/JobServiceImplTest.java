package com.matariky.jobs.jobsService.service.impl;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.jobs.jobsService.bean.form.*;
import com.matariky.jobs.jobsService.mapper.JobMapper;
import org.mockito.Mock;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

@SpringBootTest
public class JobServiceImplTest {

    @InjectMocks
    private JobServiceImpl jobServiceImpl;

    @Mock
    private Scheduler scheduler;

    @Mock
    private JobMapper jobMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddJob() throws Exception {
        JobForm form = new JobForm();
        form.setJobClassName("com.matariky.jobs.jobsService.assetitm.base.job.SampleJob");
        form.setJobGroupName("group1");
        form.setCronExpression("0/5 * * * * ?");

        jobServiceImpl.addJob(form);

        verify(scheduler, times(1)).start();
        verify(scheduler, times(1)).scheduleJob(any(), any());
    }

    @Test
    void testDeleteJob() throws SchedulerException {
        JobForm form = new JobForm();
        form.setJobClassName("com.matariky.jobs.jobsService.assetitm.base.job.SampleJob");
        form.setJobGroupName("group1");

        jobServiceImpl.deleteJob(form);

        verify(scheduler, times(1)).pauseTrigger(any());
        verify(scheduler, times(1)).unscheduleJob(any());
        verify(scheduler, times(1)).deleteJob(any());
    }

    @Test
    void testPauseJob() throws SchedulerException {
        JobForm form = new JobForm();
        form.setJobClassName("com.matariky.jobs.jobsService.assetitm.base.job.SampleJob");
        form.setJobGroupName("group1");
        form.setTenantId("tenant1");

        jobServiceImpl.pauseJob(form);

        verify(jobMapper, times(1)).pauseRequestRecoveryByMapper(form.getJobClassName(), form.getJobGroupName(),
                form.getTenantId());
        verify(jobMapper, times(1)).pauseTriggerState(form.getJobClassName(), form.getJobGroupName(),
                form.getTenantId());
    }

    @Test
    void testResumeJob() throws SchedulerException {
        JobForm form = new JobForm();
        form.setJobClassName("com.matariky.jobs.jobsService.assetitm.base.job.SampleJob");
        form.setJobGroupName("group1");

        jobServiceImpl.resumeJob(form);

        verify(scheduler, times(1)).resumeJob(any());
    }

    @Test
    void testCronJob() throws Exception {
        JobForm form = new JobForm();
        form.setJobClassName("com.matariky.jobs.jobsService.assetitm.base.job.SampleJob");
        form.setJobGroupName("group1");
        form.setCronExpression("0/5 * * * * ?");

        jobServiceImpl.cronJob(form);

        verify(scheduler, times(1)).rescheduleJob(any(), any());
    }
}
