package com.matariky.commonservice.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.matariky.annotation.DataScope;

import java.util.Collection;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import com.matariky.commonservice.base.bean.BasicBaseTraceability;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseTraceabilityMapper extends BaseMapper<BasicBaseTraceability>{
	 
@DataScope(alias="t")	public List<BasicBaseTraceability> getBasicBaseTraceabilityAll(@Param("params") BasicBaseTraceability bean);
	 
	public int getBasicBaseTraceabilityAllCount();
	 
	public int createBasicBaseTraceability(BasicBaseTraceability bean);
	 
	public int updateBasicBaseTraceability(@Param("params") BasicBaseTraceability bean);
	 
	public int delBasicBaseTraceabilityById(int id);
	 
	public BasicBaseTraceability getBasicBaseTraceabilityById(int id);
	//Insert a record
	@Override
	int insert(BasicBaseTraceability record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseTraceability entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseTraceability entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> updateWrapper);
	//Query By ID   
	@Override
	BasicBaseTraceability selectById(Serializable id);
	 
	@Override
	List<BasicBaseTraceability> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseTraceability> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	BasicBaseTraceability selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//Query All By Entity
	@Override
	List<BasicBaseTraceability> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<BasicBaseTraceability> selectPage(Page<BasicBaseTraceability> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	public List<BasicBaseTraceability> getBasicBaseTraceabilityDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseTraceabilityDACCount(@Param("params") Map<String, Object> params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseTraceability> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);


}
