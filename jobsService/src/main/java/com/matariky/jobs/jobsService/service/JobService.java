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
     * 添加并启动定时 Task 
     *
     * @param form 表单 Parameter  {@link JobForm}
     * @throws Exception 异常
     */
    void addJob(JobForm form) throws Exception;

    /**
     * @Description: 添加并启动盘点定时 Task 
     * @Author: bo.chen
     * @Date: 2023/10/16 18:21
     **/
    void addInventoryJob(InventoryJobForm form) throws Exception;

    /**
     * @Description: 添加并启动 Label  Generation 定时 Task 
     * @Author: chenyajun
     * @Date: 2024/02/16 18:21
     **/
    void addRfidCreateJob(RfidCreateJobForm form) throws Exception;

    /**
     * @Description: 添加并启动 Label  Print 定时 Task 
     * @Author: chenyajun
     * @Date: 2024/02/16 18:21
     **/
    void addRfidPrintJob(RfidPrintJobForm form) throws Exception;

    /**
     * @Description: 添加并启动 Label Import定时 Task 
     * @Author: chenyajun
     * @Date: 2024/02/16 18:21
     **/
    void addRfidUploadJob(RfidUploadJobForm form) throws Exception;

    /**
     * 删除定时 Task 
     *
     * @param form 表单 Parameter  {@link JobForm}
     * @throws SchedulerException 异常
     */
    void deleteJob(JobForm form) throws SchedulerException;

    /**
     * 暂停定时 Task 
     *
     * @param form 表单 Parameter  {@link JobForm}
     * @throws SchedulerException 异常
     */
    void pauseJob(JobForm form) throws SchedulerException;

    /**
     * 恢复定时 Task 
     *
     * @param form 表单 Parameter  {@link JobForm}
     * @throws SchedulerException 异常
     */
    void resumeJob(JobForm form) throws SchedulerException;

    /**
     * 重新配置定时 Task 
     *
     * @param form 表单 Parameter  {@link JobForm}
     * @throws Exception 异常
     */
    void cronJob(JobForm form) throws Exception;

    /**
     *  Query 定时 Task 列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @param tenantId
     * @return 定时 Task 列表
     */
    PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize, String tenantId);

	void updateTenantId( String jobGroupName,  String jobClassName, String tenantId);

	Boolean getJob(String jobClassName, String jobGroupName);


	List<JobAndTrigger> getJobAndTriggerAll(Map<String, Object> map);

    /**
     * 订单过期-定时 Task 
     * @param form
     */
    public void addOrderExpireJob(BaseJobForm form) throws Exception;

    /**
     * 保存 Device 升级定时 Task 
     * @param form
     * @throws Exception
     */
    public void addDeviceUpgradeJob(DeviceUpgradeForm form) throws Exception;
}
