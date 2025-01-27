package com.matariky.jobs.jobsService.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matariky.jobs.jobsService.bean.domain.JobAndTrigger;

/**
 * <p>
 * Job Mapper
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.mapper
 * @description: Job Mapper
 * @date: Created in 2020-11-19 10:24
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 */
public interface JobMapper extends BaseMapper<JobAndTrigger> {
        /**
         * Query Scheduled Job and trigger Pagination
         * 
         * @param tenantId
         *
         * @return Scheduled Job and trigger Pagination
         */
        List<JobAndTrigger> list(@Param("tenantId") String tenantId);

        @Update("update qrtz_job_details set tenant_id=#{tenantId} where job_group=#{jobGroupName} and job_class_name =#{jobClassName} ")
        void updateTenantId(@Param("jobGroupName") String jobGroupName, @Param("jobClassName") String jobClassName,
                        @Param("tenantId") String tenantId);

        @Select("select count(*) from qrtz_job_details where job_group=#{jobGroupName} and job_class_name =#{jobClassName}")
        int getJobByClassAndName(@Param("jobClassName") String jobClassName,
                        @Param("jobGroupName") String jobGroupName);

        @Override
        int insert(JobAndTrigger entity);

        @Override
        int deleteById(Serializable id);

        @Override
        int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

        @Override
        int delete(@Param(Constants.WRAPPER) Wrapper<JobAndTrigger> queryWrapper);

        @Override
        int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

        @Override
        int updateById(@Param(Constants.ENTITY) JobAndTrigger entity);

        @Override
        int update(@Param(Constants.ENTITY) JobAndTrigger entity,
                        @Param(Constants.WRAPPER) Wrapper<JobAndTrigger> updateWrapper);

        @Override
        JobAndTrigger selectById(Serializable id);

        @Override
        List<JobAndTrigger> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

        @Override
        List<JobAndTrigger> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

        @Override
        JobAndTrigger selectOne(@Param(Constants.WRAPPER) Wrapper<JobAndTrigger> queryWrapper);

        @Override
        Long selectCount(@Param(Constants.WRAPPER) Wrapper<JobAndTrigger> queryWrapper);

        @Override
        List<JobAndTrigger> selectList(@Param(Constants.WRAPPER) Wrapper<JobAndTrigger> queryWrapper);

        @Override
        List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<JobAndTrigger> queryWrapper);

        @Override
        List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<JobAndTrigger> queryWrapper);

        Page<JobAndTrigger> selectPage(Page<JobAndTrigger> page,
                        @Param(Constants.WRAPPER) Wrapper<JobAndTrigger> queryWrapper);

        Page<Map<String, Object>> selectMapsPage(Page<JobAndTrigger> page,
                        @Param(Constants.WRAPPER) Wrapper<JobAndTrigger> queryWrapper);

        List<JobAndTrigger> getJobAndTriggerAll(@Param("params") Map<String, Object> map);

        @Update("update qrtz_job_details set requests_recovery=true  where tenant_id=#{tenantId} and job_class_name=#{jobClassName} and job_group=#{jobGroupName}")
        void pauseRequestRecoveryByMapper(@Param("jobClassName") String jobClassName,
                        @Param("jobGroupName") String jobGroupName, @Param("tenantId") String tenantId);

        @Update("update qrtz_triggers set trigger_state='PAUSED' where job_group=#{jobGroupName} and tenant_id=#{tenantId} and trigger_name=#{jobClassName}")
        void pauseTriggerState(@Param("jobClassName") String jobClassName, @Param("jobGroupName") String jobGroupName,
                        @Param("tenantId") String tenantId);

        @Update("update qrtz_triggers set tenant_id=#{tenantId} where job_group=#{jobGroupName}  and trigger_name=#{jobClassName}")
        void updateTriggerTenantId(@Param("jobGroupName") String jobGroupName,
                        @Param("jobClassName") String jobClassName,
                        @Param("tenantId") String tenantId);
}
