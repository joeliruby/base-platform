package com.matariky.jobs.jobsService.service;

import java.util.List;
import java.util.Map;

import com.matariky.jobs.jobsService.bean.form.*;
import org.quartz.SchedulerException;

import com.github.pagehelper.PageInfo;

import com.matariky.jobs.jobsService.bean.domain.JobAndTrigger;

/**
 * <p>
 * Job Service
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.service
 * @description: Job Service
 * @version: V1.0
 */
public interface JobService {
    /**
     * Add and start scheduled task
     *
     * @param form Form parameter {@link JobForm}
     * @throws Exception Exception
     */
    void addJob(JobForm form) throws Exception;

    /**
     * @Description: Add and start inventory scheduled task
     * @Author: bo.chen
     * @Date: 2023/10/16 18:21
     **/
    void addInventoryJob(InventoryJobForm form) throws Exception;

    /**
     * @Description: Add and start label generation scheduled task
     * @Author: chenyajun
     * @Date: 2024/02/16 18:21
     **/
    void addRfidCreateJob(RfidCreateJobForm form) throws Exception;

    /**
     * @Description: Add and start label print scheduled task
     * @Author: chenyajun
     * @Date: 2024/02/16 18:21
     **/
    void addRfidPrintJob(RfidPrintJobForm form) throws Exception;

    /**
     * @Description: Add and start label import scheduled task
     * @Author: chenyajun
     * @Date: 2024/02/16 18:21
     **/
    void addRfidUploadJob(RfidUploadJobForm form) throws Exception;

    /**
     * Delete scheduled task
     *
     * @param form Form parameter {@link JobForm}
     * @throws SchedulerException Exception
     */
    void deleteJob(JobForm form) throws SchedulerException;

    /**
     * Pause scheduled task
     *
     * @param form Form parameter {@link JobForm}
     * @throws SchedulerException Exception
     */
    void pauseJob(JobForm form) throws SchedulerException;

    /**
     * Resume scheduled task
     *
     * @param form Form parameter {@link JobForm}
     * @throws SchedulerException Exception
     */
    void resumeJob(JobForm form) throws SchedulerException;

    /**
     * Reconfigure scheduled task
     *
     * @param form Form parameter {@link JobForm}
     * @throws Exception Exception
     */
    void cronJob(JobForm form) throws Exception;

    /**
     * Query scheduled task pagination
     *
     * @param currentPage Current page
     * @param pageSize    Number of items per page
     * @param tenantId
     * @return Scheduled task pagination
     */
    PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize, String tenantId);

    void updateTenantId(String jobGroupName, String jobClassName, String tenantId);

    Boolean getJob(String jobClassName, String jobGroupName);

    List<JobAndTrigger> getJobAndTriggerAll(Map<String, Object> map);

    /**
     * Order expiry - scheduled task
     * 
     * @param form
     */
    public void addOrderExpireJob(BaseJobForm form) throws Exception;

    /**
     * Save Device Upgrade Scheduled Task
     * 
     * @param form
     * @throws Exception
     */
    public void addDeviceUpgradeJob(DeviceUpgradeForm form) throws Exception;
}
