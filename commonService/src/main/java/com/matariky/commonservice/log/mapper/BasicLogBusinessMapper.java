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
import com.matariky.commonservice.log.bean.BasicLogBusiness;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicLogBusinessMapper extends BaseMapper<BasicLogBusiness> {

	public List<BasicLogBusiness> getBasicLogBusinessAll();

	public int getBasicLogBusinessAllCount();

	public int createBasicLogBusiness(BasicLogBusiness bean);

	public int updateBasicLogBusiness(@Param("params") BasicLogBusiness bean);

	public int delBasicLogBusinessById(int id);

	public BasicLogBusiness getBasicLogBusinessById(int id);

	// Insert a record
	@Override
	int insert(BasicLogBusiness record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicLogBusiness entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicLogBusiness entity,
			@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> updateWrapper);

	// Query By ID
	@Override
	BasicLogBusiness selectById(Serializable id);

	@Override
	List<BasicLogBusiness> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicLogBusiness> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query One By Entity
	@Override
	BasicLogBusiness selectOne(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);

	// Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);

	// Query All By Entity
	@Override
	List<BasicLogBusiness> selectList(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<BasicLogBusiness> selectPage(Page<BasicLogBusiness> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);

	public List<BasicLogBusiness> getBasicLogBusinessDAC(@Param("params") Map<String, Object> params);

	public Long getBasicLogBusinessDACCount(@Param("params") Map<String, Object> params);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicLogBusiness> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);

}
