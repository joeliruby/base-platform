package com.matariky.clickhouse.logs.service;

import com.matariky.clickhouse.logs.entity.Logs;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;

public interface ILogsService extends IService<Logs> {
	
	public Page<Logs> getTracesAll(@Param("params") Logs logs, Integer index, Integer perPage);

	Long getAppTracesCount(@Param("params")Logs logs);


	public void export(Logs logs,Integer index, Integer perPage);
}
