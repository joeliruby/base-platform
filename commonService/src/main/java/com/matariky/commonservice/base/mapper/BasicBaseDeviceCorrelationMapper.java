package com.matariky.commonservice.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.matariky.annotation.DataScope;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import com.matariky.commonservice.base.bean.BasicBaseDeviceCorrelation;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseDeviceCorrelationMapper extends BaseMapper<BasicBaseDeviceCorrelation> {

	@DataScope(alias = "t")
	public List<BasicBaseDeviceCorrelation> getBasicBaseDeviceCorrelationAll(
			@Param("params") BasicBaseDeviceCorrelation bean);

	public int getBasicBaseDeviceCorrelationAllCount();

	public int createBasicBaseDeviceCorrelation(BasicBaseDeviceCorrelation bean);

	public int updateBasicBaseDeviceCorrelation(@Param("params") BasicBaseDeviceCorrelation bean);

	public int delBasicBaseDeviceCorrelationById(int id);

	public BasicBaseDeviceCorrelation getBasicBaseDeviceCorrelationById(int id);

	// Insert a record
	@Override
	int insert(BasicBaseDeviceCorrelation record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseDeviceCorrelation entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseDeviceCorrelation entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> updateWrapper);

	// Query By ID
	@Override
	BasicBaseDeviceCorrelation selectById(Serializable id);

	@Override
	List<BasicBaseDeviceCorrelation> selectBatchIds(
			@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseDeviceCorrelation> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query One By Entity
	@Override
	BasicBaseDeviceCorrelation selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);

	// Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);

	// Query All By Entity
	@Override
	List<BasicBaseDeviceCorrelation> selectList(
			@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<BasicBaseDeviceCorrelation> selectPage(Page<BasicBaseDeviceCorrelation> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);

	public List<BasicBaseDeviceCorrelation> getBasicBaseDeviceCorrelationDAC(
			@Param("params") Map<String, Object> params);

	public Long getBasicBaseDeviceCorrelationDACCount(@Param("params") Map<String, Object> params);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseDeviceCorrelation> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);

}
