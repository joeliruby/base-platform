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
     * Add 并启动 Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @throws Exception 异常
     */
    void addJob(JobForm form) throws Exception;

    /**
     * @Description: Add 并启动盘点 Scheduled Task
     * @Author: bo.chen
     * @Date: 2023/10/16 18:21
     **/
    void addInventoryJob(InventoryJobForm form) throws Exception;

    /**
     * @Description: Add 并启动 Label Generation Scheduled Task
     * @Author: chenyajun
     * @Date: 2024/02/16 18:21
     **/
    void addRfidCreateJob(RfidCreateJobForm form) throws Exception;

    /**
     * @Description: Add 并启动 Label Print Scheduled Task
     * @Author: chenyajun
     * @Date: 2024/02/16 18:21
     **/
    void addRfidPrintJob(RfidPrintJobForm form) throws Exception;

    /**
     * @Description: Add 并启动 Label Import Scheduled Task
     * @Author: chenyajun
     * @Date: 2024/02/16 18:21
     **/
    void addRfidUploadJob(RfidUploadJobForm form) throws Exception;

    /**
     * Delete Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @throws SchedulerException 异常
     */
    void deleteJob(JobForm form) throws SchedulerException;

    /**
     * 暂停 Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @throws SchedulerException 异常
     */
    void pauseJob(JobForm form) throws SchedulerException;

    /**
     * 恢复 Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @throws SchedulerException 异常
     */
    void resumeJob(JobForm form) throws SchedulerException;

    /**
     * 重新 Configuration Scheduled Task
     *
     * @param form 表单 Parameter {@link JobForm}
     * @throws Exception 异常
     */
    void cronJob(JobForm form) throws Exception;

    /**
     * Query Scheduled Task Pagination
     *
     * @param currentPage Current 页
     * @param pageSize    每页条数
     * @param tenantId
     * @return Scheduled Task Pagination
     */
    PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize, String tenantId);

    void updateTenantId(String jobGroupName, String jobClassName, String tenantId);

    Boolean getJob(String jobClassName, String jobGroupName);

    List<JobAndTrigger> getJobAndTriggerAll(Map<String, Object> map);

    /**
     * 订单过期- Scheduled Task
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
