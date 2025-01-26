package com.matariky.clickhouse.logs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.Logs;

@Mapper
public interface LogsMapper extends BaseMapper<Logs> {

	public Page<Logs> getAppTracesAll(@Param("params")Logs logs);
	
	public Long getAppTracesCount(@Param("params")Logs logs);

}
