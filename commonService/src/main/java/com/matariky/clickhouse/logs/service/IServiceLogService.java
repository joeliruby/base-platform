package com.matariky.clickhouse.logs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.ServiceLog;
import org.apache.ibatis.annotations.Param;

public interface IServiceLogService extends IService<ServiceLog> {
    public Page<ServiceLog> getTracesAll(@Param("params") ServiceLog logs, Integer index, Integer perPage);

    Long getAppTracesCount(@Param("params") ServiceLog logs);


    public void export(ServiceLog logs);
}
