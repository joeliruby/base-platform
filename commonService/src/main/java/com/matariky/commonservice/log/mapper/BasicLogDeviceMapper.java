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
import com.matariky.commonservice.log.bean.BasicLogDevice;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicLogDeviceMapper extends BaseMapper<BasicLogDevice> {

	public List<BasicLogDevice> getBasicLogDeviceAll();

	public int getBasicLogDeviceAllCount();

	public int createBasicLogDevice(BasicLogDevice bean);

	public int updateBasicLogDevice(@Param("params") BasicLogDevice bean);

	public int delBasicLogDeviceById(int id);

	public BasicLogDevice getBasicLogDeviceById(int id);

	// Insert a record
	@Override
	int insert(BasicLogDevice record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicLogDevice> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicLogDevice entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicLogDevice entity,
			@Param(Constants.WRAPPER) Wrapper<BasicLogDevice> updateWrapper);

	// Query By ID
	@Override
	BasicLogDevice selectById(Serializable id);

	@Override
	List<BasicLogDevice> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicLogDevice> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query One By Entity
	@Override
	BasicLogDevice selectOne(@Param(Constants.WRAPPER) Wrapper<BasicLogDevice> queryWrapper);

	// Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicLogDevice> queryWrapper);

	// Query All By Entity
	@Override
	List<BasicLogDevice> selectList(@Param(Constants.WRAPPER) Wrapper<BasicLogDevice> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicLogDevice> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicLogDevice> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<BasicLogDevice> selectPage(Page<BasicLogDevice> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogDevice> queryWrapper);

	public List<BasicLogDevice> getBasicLogDeviceDAC(@Param("params") Map<String, Object> params);

	public Long getBasicLogDeviceDACCount(@Param("params") Map<String, Object> params);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicLogDevice> page,
			@Param(Constants.WRAPPER) Wrapper<BasicLogDevice> queryWrapper);

}
