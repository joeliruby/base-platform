package com.matariky.commonservice.log.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import com.matariky.commonservice.log.bean.BasicLogPrint;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicLogPrintMapper extends BaseMapper<BasicLogPrint> {

	public List<BasicLogPrint> getBasicLogPrintAll();

	public int getBasicLogPrintAllCount();

	public int createBasicLogPrint(BasicLogPrint bean);

	public int updateBasicLogPrint(@Param("params") BasicLogPrint bean);

	public int delBasicLogPrintById(int id);

	public BasicLogPrint getBasicLogPrintById(int id);

	// Insert a record
	@Override
	int insert(BasicLogPrint record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicLogPrint entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicLogPrint entity,
			@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> updateWrapper);

	// Query By ID
	@Override
	BasicLogPrint selectById(Serializable id);

	@Override
	List<BasicLogPrint> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicLogPrint> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query One By Entity
	@Override
	BasicLogPrint selectOne(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);

	// Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);

	// Query All By Entity
	@Override
	List<BasicLogPrint> selectList(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<BasicLogPrint> selectPage(Page<BasicLogPrint> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);

	public List<BasicLogPrint> getBasicLogPrintDAC(@Param("params") Map<String, Object> params);

	public Long getBasicLogPrintDACCount(@Param("params") Map<String, Object> params);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicLogPrint> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);

}
