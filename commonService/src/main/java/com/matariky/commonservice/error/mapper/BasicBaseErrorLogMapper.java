package com.matariky.commonservice.error.mapper;

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
import com.matariky.commonservice.error.bean.BasicBaseErrorLog;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseErrorLogMapper extends BaseMapper<BasicBaseErrorLog>{
	 
	@DataScope(alias="a")
	public List<BasicBaseErrorLog> getBasicBaseErrorLogAll(@Param("params") BasicBaseErrorLog bean);
	 
	@DataScope
	public int getBasicBaseErrorLogAllCount();
	 
	public int createBasicBaseErrorLog(BasicBaseErrorLog bean);
	 
	public int updateBasicBaseErrorLog(@Param("params") BasicBaseErrorLog bean);
	 
	public int delBasicBaseErrorLogById(int id);
	 
	public BasicBaseErrorLog getBasicBaseErrorLogById(int id);
	//Insert a record
	@Override
	int insert(BasicBaseErrorLog record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseErrorLog entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseErrorLog entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> updateWrapper);
	//Query By ID 
	@Override
	BasicBaseErrorLog selectById(Serializable id);
	 
	@Override
	List<BasicBaseErrorLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseErrorLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	BasicBaseErrorLog selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//Query All By Entity
	@Override
	List<BasicBaseErrorLog> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<BasicBaseErrorLog> selectPage(Page<BasicBaseErrorLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	public List<BasicBaseErrorLog> getBasicBaseErrorLogDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseErrorLogDACCount(@Param("params") Map<String, Object> params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseErrorLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);


}
