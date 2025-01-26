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
	//插入一条记录
	@Override
	int insert(BasicBaseDeviceLog record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseDeviceLog entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseDeviceLog entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> updateWrapper);
	//根据 ID  Query 
	@Override
	BasicBaseDeviceLog selectById(Serializable id);
	 
	@Override
	List<BasicBaseDeviceLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseDeviceLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseDeviceLog selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseDeviceLog> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseDeviceLog> selectPage(Page<BasicBaseDeviceLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);
	public List<BasicBaseDeviceLog> getBasicBaseDeviceLogDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseDeviceLogDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseDeviceLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceLog> queryWrapper);


}
