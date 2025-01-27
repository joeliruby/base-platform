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
import com.matariky.commonservice.log.bean.BasicLogError;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicLogErrorMapper extends BaseMapper<BasicLogError> {

	public List<BasicLogError> getBasicLogErrorAll();

	public int getBasicLogErrorAllCount();

	public int createBasicLogError(BasicLogError bean);

	public int updateBasicLogError(@Param("params") BasicLogError bean);

	public int delBasicLogErrorById(int id);

	public BasicLogError getBasicLogErrorById(int id);

	// Insert a record
	@Override
	int insert(BasicLogError record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicLogError entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicLogError entity,
			@Param(Constants.WRAPPER) Wrapper<BasicLogError> updateWrapper);

	// Query By ID
	@Override
	BasicLogError selectById(Serializable id);

	@Override
	List<BasicLogError> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicLogError> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query One By Entity
	@Override
	BasicLogError selectOne(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);

	// Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);

	// Query All By Entity
	@Override
	List<BasicLogError> selectList(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<BasicLogError> selectPage(Page<BasicLogError> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);

	public List<BasicLogError> getBasicLogErrorDAC(@Param("params") Map<String, Object> params);

	public Long getBasicLogErrorDACCount(@Param("params") Map<String, Object> params);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicLogError> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);

}
