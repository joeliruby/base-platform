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
import com.matariky.commonservice.log.bean.BasicLogNetwork;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicLogNetworkMapper extends BaseMapper<BasicLogNetwork> {

	public List<BasicLogNetwork> getBasicLogNetworkAll();

	public int getBasicLogNetworkAllCount();

	public int createBasicLogNetwork(BasicLogNetwork bean);

	public int updateBasicLogNetwork(@Param("params") BasicLogNetwork bean);

	public int delBasicLogNetworkById(int id);

	public BasicLogNetwork getBasicLogNetworkById(int id);

	// Insert a record
	@Override
	int insert(BasicLogNetwork record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicLogNetwork> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicLogNetwork entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicLogNetwork entity,
			@Param(Constants.WRAPPER) Wrapper<BasicLogNetwork> updateWrapper);

	// Query By ID
	@Override
	BasicLogNetwork selectById(Serializable id);

	@Override
	List<BasicLogNetwork> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicLogNetwork> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query One By Entity
	@Override
	BasicLogNetwork selectOne(@Param(Constants.WRAPPER) Wrapper<BasicLogNetwork> queryWrapper);

	// Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicLogNetwork> queryWrapper);

	// Query All By Entity
	@Override
	List<BasicLogNetwork> selectList(@Param(Constants.WRAPPER) Wrapper<BasicLogNetwork> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicLogNetwork> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicLogNetwork> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<BasicLogNetwork> selectPage(Page<BasicLogNetwork> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogNetwork> queryWrapper);

	public List<BasicLogNetwork> getBasicLogNetworkDAC(@Param("params") Map<String, Object> params);

	public Long getBasicLogNetworkDACCount(@Param("params") Map<String, Object> params);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicLogNetwork> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogNetwork> queryWrapper);

}
