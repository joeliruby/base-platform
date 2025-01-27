package com.matariky.commonservice.printlog.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import com.matariky.annotation.DataScope;
import com.matariky.commonservice.printlog.bean.BasicBasePrintLog;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBasePrintLogMapper extends BaseMapper<BasicBasePrintLog> {

	@DataScope(alias = "a")
	public List<BasicBasePrintLog> getBasicBasePrintLogAll(@Param("params") BasicBasePrintLog bean);

	@DataScope
	public int getBasicBasePrintLogAllCount();

	public int createBasicBasePrintLog(BasicBasePrintLog bean);

	public int updateBasicBasePrintLog(@Param("params") BasicBasePrintLog bean);

	public int delBasicBasePrintLogById(int id);

	public BasicBasePrintLog getBasicBasePrintLogById(int id);

	// Insert a record
	@Override
	int insert(BasicBasePrintLog record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBasePrintLog entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBasePrintLog entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> updateWrapper);

	// Query By ID
	@Override
	BasicBasePrintLog selectById(Serializable id);

	@Override
	List<BasicBasePrintLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBasePrintLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query One By Entity
	@Override
	BasicBasePrintLog selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);

	// Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);

	// Query All By Entity
	@Override
	List<BasicBasePrintLog> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<BasicBasePrintLog> selectPage(Page<BasicBasePrintLog> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);

	public List<BasicBasePrintLog> getBasicBasePrintLogDAC(@Param("params") Map<String, Object> params);

	public Long getBasicBasePrintLogDACCount(@Param("params") Map<String, Object> params);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBasePrintLog> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);

}
