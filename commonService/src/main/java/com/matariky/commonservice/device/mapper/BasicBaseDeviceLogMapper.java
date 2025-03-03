package com.matariky.commonservice.device.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.device.bean.BasicBaseDeviceLog;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseDeviceLogMapper extends BaseMapper<BasicBaseDeviceLog>{
	 
	@DataScope(alias="a")
	public List<BasicBaseDeviceLog> getBasicBaseDeviceLogAll(@Param("params") BasicBaseDeviceLog log);
	 
	@DataScope
	public int getBasicBaseDeviceLogAllCount();
	 
	public int createBasicBaseDeviceLog(BasicBaseDeviceLog bean);
	 
	public int updateBasicBaseDeviceLog(@Param("params") BasicBaseDeviceLog bean);
	 
	public int delBasicBaseDeviceLogById(int id);
	 
	public BasicBaseDeviceLog getBasicBaseDeviceLogById(int id);
	//Insert a record
	@Override
	int insert(BasicBaseDeviceLog record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseDeviceLog entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseDeviceLog entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> updateWrapper);
	//Query By ID 
	@Override
	BasicBaseDeviceLog selectById(Serializable id);
	 
	@Override
	List<BasicBaseDeviceLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseDeviceLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	BasicBaseDeviceLog selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//Query All By Entity
	@Override
	List<BasicBaseDeviceLog> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<BasicBaseDeviceLog> selectPage(Page<BasicBaseDeviceLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	public List<BasicBaseDeviceLog> getBasicBaseDeviceLogDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseDeviceLogDACCount(@Param("params") Map<String, Object> params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseDeviceLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);


}
