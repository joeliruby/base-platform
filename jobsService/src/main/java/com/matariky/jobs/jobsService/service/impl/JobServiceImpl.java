package com.matariky.jobs.jobsService.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.jobs.jobsService.assetitm.base.job.*;
import com.matariky.jobs.jobsService.assetitm.inventory.job.TapeInventoryTaskJob;
import com.matariky.jobs.jobsService.bean.domain.JobAndTrigger;
import com.matariky.jobs.jobsService.bean.form.*;
import com.matariky.jobs.jobsService.mapper.JobMapper;
import com.matariky.jobs.jobsService.service.JobService;
import com.matariky.jobs.jobsService.util.JobUtil;
import com.matariky.utils.DateUtil;
import com.matariky.utils.NumberUtils;
import com.matariky.utils.StringUtils;
import com.matariky.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * Job Service
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.service.impl
 * @description: Job Service
 * @version: V1.0
 */
@Service
@Slf4j
public class JobServiceImpl implements JobService {
    private final Scheduler scheduler;
    private final JobMapper jobMapper;

    @Autowired
    public JobServiceImpl(@Qualifier("quartzScheduler") Scheduler scheduler, JobMapper jobMapper) {
        this.scheduler = scheduler;
        this.jobMapper = jobMapper;
    }

    /**
     * Add 并启动 Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @return {@link JobDetail}
     * @throws Exception 异常
     */
    @Override
    public void addJob(JobForm form) throws Exception {
        // 启动调度器
        scheduler.start();
        // 构建Job Information
        JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(form.getJobClassName()).getClass())
                .withIdentity(form.getJobClassName(), form.getJobGroupName()).build();
        Trigger trigger;
        if (StringUtils.isNotEmpty(form.getCronExpression())) {
            // Cron表达式调度构建器(即 Task 执行的 Time )
            CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(form.getCronExpression());
            // 根据Cron表达式构建 one Trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(form.getJobClassName(), form.getJobGroupName())
                    .withSchedule(cron).build();
        } else {
            /** 立即启动 **/
            trigger = TriggerBuilder.newTrigger().withIdentity(form.getJobClassName(), form.getJobGroupName())
                    .startNow().build();
        }
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("【 Scheduled  Task 】Create Failed！", e);
            throw new Exception("【 Scheduled  Task 】Create Failed！");
        }
    }

    @Override
    public void addInventoryJob(InventoryJobForm form) throws Exception {
        // 启动调度器
        scheduler.start();
        // 构建Job Information
        JobDetail jobDetail = JobBuilder.newJob(TapeInventoryTaskJob.class)
                .withIdentity(TapeInventoryTaskJob.class.getName(), form.getTaskId().toString()).build();
        Trigger trigger;
        if (NumberUtils.INTEGER_ONE.equals(form.getTaskType())) {
            /** 立即启动 **/
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(TapeInventoryTaskJob.class.getName(), form.getTaskId().toString()).startNow().build();
        } else if (NumberUtils.INTEGER_TWO.equals(form.getTaskType())) {
            CronScheduleBuilder cron = CronScheduleBuilder
                    .cronSchedule(DateUtil.getCron(DateUtil.toLocalDateTime(form.getStartTime())));
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(TapeInventoryTaskJob.class.getName(), form.getTaskId().toString()).withSchedule(cron)
                    .build();
        } else {
            DateBuilder.IntervalUnit unit;
            if (form.getIntervalUnit() == 1) {
                unit = DateBuilder.IntervalUnit.HOUR;
            } else if (form.getIntervalUnit() == 2) {
                unit = DateBuilder.IntervalUnit.DAY;
            } else if (form.getIntervalUnit() == 3) {
                unit = DateBuilder.IntervalUnit.MONTH;
            } else {
                unit = DateBuilder.IntervalUnit.WEEK;
            }
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(TapeInventoryTaskJob.class.getName(), form.getTaskId().toString())
                    .startAt(new Date(form.getStartTime()))
                    .endAt(Objects.nonNull(form.getEndTime()) ? new Date(form.getEndTime()) : null)
                    .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                            .withInterval(form.getTimeInterval(), unit))
                    .build();
        }
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new Exception("【 Scheduled  Task 】Create Failed！", e);
        }
    }

    @Override
    public void addRfidCreateJob(RfidCreateJobForm form) throws Exception {
        // 启动调度器
        scheduler.start();
        // 构建Job Information
        JobDetail jobDetail = JobBuilder.newJob(TapeRfidCreateTaskJob.class)
                .withIdentity(TapeRfidCreateTaskJob.class.getName(), form.getTaskId().toString()).build();
        // Trigger trigger;
        // if (NumberUtils.INTEGER_ONE.equals(form.getTaskType())) {
        /** 立即启动 **/
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TapeRfidCreateTaskJob.class.getName(), form.getTaskId().toString()).startNow().build();
        // }
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new Exception("【 Scheduled  Task 】Create Failed！", e);
        }
    }

    @Override
    public void addRfidPrintJob(RfidPrintJobForm form) throws Exception {
        // 启动调度器
        scheduler.start();
        // 构建Job Information
        JobDetail jobDetail = JobBuilder.newJob(TapeRfidPrintTaskJob.class)
                .withIdentity(TapeRfidPrintTaskJob.class.getName(), form.getTaskId().toString()).build();
        // Trigger trigger;
        // if (NumberUtils.INTEGER_ONE.equals(form.getTaskType())) {
        /** 立即启动 **/
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TapeRfidPrintTaskJob.class.getName(), form.getTaskId().toString()).startNow().build();
        // }
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new Exception("【 Scheduled  Task 】Create Failed！", e);
        }
    }

    @Override
    public void addRfidUploadJob(RfidUploadJobForm form) throws Exception {
        // 启动调度器
        scheduler.start();
        // 构建Job Information
        JobDetail jobDetail = JobBuilder.newJob(TapeRfidUploadTaskJob.class)
                .withIdentity(TapeRfidUploadTaskJob.class.getName(), form.getTaskId().toString()).build();
        // Trigger trigger;
        // if (NumberUtils.INTEGER_ONE.equals(form.getTaskType())) {
        /** 立即启动 **/
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TapeRfidUploadTaskJob.class.getName(), form.getTaskId().toString()).startNow().build();
        // }
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new Exception("【 Scheduled  Task 】Create Failed！", e);
        }
    }

    /**
     * Delete Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @throws SchedulerException 异常
     */
    @Override
    public void deleteJob(JobForm form) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
        scheduler.unscheduleJob(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
        scheduler.deleteJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
    }

    /**
     * 暂停 Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @throws SchedulerException 异常
     */
    @Override
    public void pauseJob(JobForm form) throws SchedulerException {
        jobMapper.pauseRequestRecoveryByMapper(form.getJobClassName(), form.getJobGroupName(), form.getTenantId());
        jobMapper.pauseTriggerState(form.getJobClassName(), form.getJobGroupName(), form.getTenantId());
        // scheduler.pauseJob(JobKey.jobKey(form.getJobClassName(),
        // form.getJobGroupName()));
    }

    /**
     * 恢复 Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @throws SchedulerException 异常
     */
    @Override
    public void resumeJob(JobForm form) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
    }

    /**
     * 重新 Configuration Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @throws Exception 异常
     */
    @Override
    public void cronJob(JobForm form) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 根据Cron表达式构建 one Trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新 Configurationjob执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("【 Scheduled  Task 】UpdateFailed！", e);
            throw new Exception("【 Scheduled  Task 】Create Failed！");
        }
    }

    /**
     * 订单过期- Scheduled Task
     *
     * @param form
     */
    @Override
    public void addOrderExpireJob(BaseJobForm form) throws Exception {
        scheduler.start();
        // 构建Job Information
        JobDetail jobDetail = JobBuilder.newJob(OrderExpireJob.class)
                .withIdentity(OrderExpireJob.class.getName(), UUIDUtil.getUUID()).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());
        /** Scheduled Task **/
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new Exception("【 Scheduled  Task 】Create Failed！", e);
        }
    }

    /**
     * Device Upgrade Scheduled Task
     *
     * @param form
     * @throws Exception
     */
    @Override
    public void addDeviceUpgradeJob(DeviceUpgradeForm form) throws Exception {
        /** 先Delete 之前 Scheduled Task **/
        JobForm deletJob = new JobForm();
        String groupName = form.getDeviceId() + "_" + form.getPackageId();
        deletJob.setJobClassName(DeviceUpgradeJob.class.getName());
        deletJob.setJobGroupName(groupName);
        this.deleteJob(deletJob);

        Date date = new Date(form.getUpgradeTime());
        SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ?");
        String cronExpression = sdf.format(date);
        form.setCronExpression(cronExpression);

        scheduler.start();
        // 构建Job Information
        JobDetail jobDetail = JobBuilder.newJob(DeviceUpgradeJob.class)
                .withIdentity(DeviceUpgradeJob.class.getName(), groupName).build();
        jobDetail.getJobDataMap().put("deviceId", form.getDeviceId());
        jobDetail.getJobDataMap().put("packageId", form.getPackageId());
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());
        /** Scheduled Task **/
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new Exception("【 Scheduled  Task 】Create Failed！", e);
        }
    }

    /**
     * Query Scheduled Task Pagination
     *
     * @param currentPage Current 页
     * @param pageSize    每页条数
     * @return Scheduled Task Pagination
     */
    @Override
    public PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize, String tenantId) {
        PageHelper.startPage(currentPage, pageSize);
        List<JobAndTrigger> list = jobMapper.list(tenantId);
        return new PageInfo<>(list);
    }

    @Override
    public void updateTenantId(String jobGroupName, String jobClassName, String tenantId) {
        jobMapper.updateTenantId(jobGroupName, jobClassName, tenantId);
        jobMapper.updateTriggerTenantId(jobGroupName, jobClassName, tenantId);
    }

    @Override
    public Boolean getJob(String jobClassName, String jobGroupName) {
        int count = jobMapper.getJobByClassAndName(jobClassName, jobGroupName);
        return count > 0;
    }

    @Override
    public List<JobAndTrigger> getJobAndTriggerAll(Map<String, Object> map) {

        return jobMapper.getJobAndTriggerAll(map);
    }

}
