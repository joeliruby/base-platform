package com.matariky.clickhouse.logs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.Logs;
import com.matariky.clickhouse.logs.entity.ServiceLog;

@Mapper
public interface ServiceLogMapper extends BaseMapper<ServiceLog> {

	public Page<ServiceLog> getAppTracesAll(@Param("params")ServiceLog logs);
	
	public Long getAppTracesCount(@Param("params")ServiceLog logs);

}
